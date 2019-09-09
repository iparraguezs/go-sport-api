package cl.go.sport.api.persistence.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cl.go.sport.api.utils.DateUtils;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User extends EntityBase {
	private static final long serialVersionUID = -5731260521355518321L;

	@Column(name = "username", length = 30, nullable = false, unique = true)
	private String username;

	@Column(name = "password", length = 100, nullable = false)
	private String password;

	@Column(name = "last_password", length = 100, nullable = true)
	private String lastPassword;

	@Temporal(TemporalType.DATE)
	@Column(name = "password_reset_at")
	private Date passwordResetAt;

	@Column(name = "email", length = 100, nullable = false, unique = true)
	private String email;

	@JsonIgnore
	@Builder.Default
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Assignment> assignments = new ArrayList<>();
	
	public boolean mustChangePassword() {
		return this.passwordResetAt != null && DateUtils.beforeToday(this.passwordResetAt);
	}
	
	public void add(Assignment assignment) {
		assignments.add(assignment);
	}

	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return assignments
			.stream()
			.map(assignment -> new SimpleGrantedAuthority(assignment.getRole().getName()))
			.collect(Collectors.toList());
	}
	
	public List<Role> getRoles() {
		return assignments
			.stream()
			.map(Assignment::getRole)
			.collect(Collectors.toList());
	}

	public String getFullname() {
		return getUsername().concat(" (").concat(getEmail()).concat(")");
	}
}
