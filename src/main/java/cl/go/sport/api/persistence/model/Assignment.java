package cl.go.sport.api.persistence.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "assignment"
	, uniqueConstraints = { @UniqueConstraint(columnNames = {"user_id", "role_id"})})
public class Assignment extends EntityBase {
	private static final long serialVersionUID = -2484249599382362060L;
	
	@ManyToOne
	@JoinColumn(name = "user_id", updatable = false, nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "role_id", updatable = false, nullable = false)
	private Role role;
}
