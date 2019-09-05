package cl.go.sport.api.controllers;

import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cl.go.sport.api.config.security.exceptions.JwtAuthenticationException;
import cl.go.sport.api.config.security.request.JwtAuthenticationRequest;
import cl.go.sport.api.config.security.response.JwtAuthenticationResponse;
import cl.go.sport.api.controllers.dto.RoleDTO;
import cl.go.sport.api.controllers.dto.UserDTO;
import cl.go.sport.api.persistence.model.User;
import cl.go.sport.api.services.UserService;
import cl.go.sport.api.services.results.ServiceResult;
import cl.go.sport.api.utils.JwtTokenUtil;

@RestController
public class JwtAuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userService;

	@PostMapping(path = "${jwt.route.authentication.path}")
	public ResponseEntity<JwtAuthenticationResponse> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest)
			throws JwtAuthenticationException {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final ServiceResult<User> optionalUser = userService.findByUsername(authenticationRequest.getUsername());
		User user = optionalUser.getResult();
		return ResponseEntity.ok(createJwtResponse(jwtTokenUtil.generateToken(user), user));
	}

	private JwtAuthenticationResponse createJwtResponse(final String token, User user) {
		return JwtAuthenticationResponse.builder()
				.token(token)
				.user(
					UserDTO.builder()
					.username(user.getUsername())
					.email(user.getEmail())
					.roles(
						user.getRoles()
						.stream()
						.map(
							r -> RoleDTO.builder()
								.name(r.getName())
								.functions(
									r.getFunctions()
									.stream()
									.map(f -> f.getFunction().getName())
									.collect(Collectors.toList())
								)
								.build()
						)
						.collect(Collectors.toList())
					)
					.build()
				)
				.build();
	}

	@GetMapping(path = "${jwt.route.authentication.refresh}")
	public ResponseEntity<JwtAuthenticationResponse> refreshAndGetAuthenticationToken(HttpServletRequest request) {
		final String token = jwtTokenUtil.retrieveTokenFromRequest(request);
		String username = jwtTokenUtil.getUsernameFromToken(token);
		final User user = userService.findByUsername(username).getResult();

		if (jwtTokenUtil.canTokenBeRefreshed(token, user.getPasswordResetAt())) {
			return ResponseEntity.ok(createJwtResponse(jwtTokenUtil.refreshToken(token), user));
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@ExceptionHandler({ JwtAuthenticationException.class })
	public ResponseEntity<String> handleAuthenticationException(JwtAuthenticationException e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
	}

	/**
	 * Authenticates the user. If something is wrong, an
	 * {@link JwtAuthenticationException} will be thrown
	 */
	private void authenticate(String username, String password) {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new JwtAuthenticationException("UserForm is disabled!", e);
		} catch (BadCredentialsException e) {
			throw new JwtAuthenticationException("Bad credentials!", e);
		}
	}

	@GetMapping(path = "${app.route.status}")
	public ResponseEntity<String> getStatus() {
		return ResponseEntity.ok("Status OK!");
	}
}
