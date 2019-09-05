package cl.go.sport.api.config.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import cl.go.sport.api.config.security.filter.JwtAuthorizationTokenFilter;
import cl.go.sport.api.services.UserService;
import cl.go.sport.api.utils.JwtTokenUtil;

@Configuration
@EnableWebSecurity
public class GoSportSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

	@Autowired
	private GoSportAuthenticationEntryPoint unauthorizedHandler;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private UserService userService;

	@Value("${jwt.header:Authorization}")
	private String tokenHeader;
	
	@Value("#{'${app.http-security.authorize-request}'.split(',')}")
	private List<String> authorizeRequest;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoderBean());
	}

	@Bean
	public PasswordEncoder passwordEncoderBean() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			// we don't need CSRF because our token is invulnerable
			.csrf().disable()
			.exceptionHandling()
			.authenticationEntryPoint(unauthorizedHandler).and()
			// don't create session
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeRequests()
			.antMatchers(authorizeRequest.stream().toArray(String[]::new)).permitAll();

		httpSecurity.authorizeRequests().anyRequest().authenticated();

		// Custom JWT based security filter

		httpSecurity.addFilterBefore(
				new JwtAuthorizationTokenFilter(userService, jwtTokenUtil, tokenHeader)
				, UsernamePasswordAuthenticationFilter.class
		);

		// disable page caching, required to set for H2 else H2 Console will be blank.
		httpSecurity.headers().frameOptions().sameOrigin().cacheControl();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// AuthenticationTokenFilter will ignore the below paths
		web.ignoring()
				.antMatchers(authorizeRequest.stream().toArray(String[]::new));
	}
}
