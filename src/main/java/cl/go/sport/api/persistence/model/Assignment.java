package cl.go.sport.api.persistence.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "assignment"
	, uniqueConstraints = { @UniqueConstraint(columnNames = {"user_id", "role_id"})})
public class Assignment extends EntityBase {
	private static final long serialVersionUID = -2484249599382362060L;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id", updatable = false, nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "role_id", updatable = false, nullable = false)
	private Role role;
}
