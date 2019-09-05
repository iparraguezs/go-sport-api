package cl.go.sport.api.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import cl.go.sport.api.persistence.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
	
	private User user;

	/** * */
	private static final long serialVersionUID = 1055915891338581331L;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.getAuthorities();
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return !user.isExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return !user.isLocked(); // TODO revisar el bloqueo en caso de N intentos fallidos
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !user.mustChangePassword();
	}

	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}

	public User getUser() {
		return user;
	}
}
