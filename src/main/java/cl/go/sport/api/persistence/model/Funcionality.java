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
@Table(name = "functionality"
	, uniqueConstraints = { @UniqueConstraint(columnNames = {"role_id", "function_id"})})
public class Funcionality extends EntityBase {
	private static final long serialVersionUID = -2484249599382362060L;
	@ManyToOne
	@JoinColumn(name = "role_id", updatable = false, nullable = false)
	private Role role;
	
	@ManyToOne
	@JoinColumn(name = "function_id", updatable = false, nullable = false)
	private Function function;
}
