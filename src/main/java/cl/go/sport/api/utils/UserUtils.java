package cl.go.sport.api.utils;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import cl.go.sport.api.config.security.GoSportUserDetails;
import cl.go.sport.api.controllers.dto.UserDTO;
import cl.go.sport.api.persistence.model.User;

@Component
public class UserUtils extends AbstractDTOUtils<User, UserDTO> {
	
	@Autowired
	private RoleUtils roleUtils;

	public static Optional<User> getAuthenticatedUser() {
		return getAuthenticatedUser(SecurityContextHolder.getContext().getAuthentication());
	}

	public static Optional<User> getAuthenticatedUser(Authentication authentication) {
		return isValidAuthentication(authentication) ? 
				Optional.of(((GoSportUserDetails) authentication.getPrincipal()).getUser()) : 
					Optional.empty();
	}

	public static boolean isValidAuthentication(Authentication authentication) {
		return !Objects.isNull(authentication) && 
				(!isAnonymousAuthentication(authentication) && authentication.isAuthenticated());
	}

	public static boolean isAnonymousAuthentication(Authentication authentication) {
		return authentication instanceof AnonymousAuthenticationToken;
	}

	@Override
	public UserDTO full(User entity) {
		UserDTO dto = UserDTO.builder()
				.email(entity.getEmail())
				.roles(roleUtils.list(entity.getRoles()))
				.username(entity.getUsername())
				.build();
		return common(dto, entity);
	}

	@Override
	public UserDTO forList(User entity) {
		return UserDTO.builder()
				.pk(entity.getId())
				.username(entity.getUsername())
				.build();
	}
	
	
}
