package cl.go.sport.api.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "function")
public class Function extends EntityBase {
	private static final long serialVersionUID = 328155766745861459L;

	@Column(name = "code", length = 10, nullable = false, unique = true)
	private String code;
	
	@Column(name = "name", length = 50, nullable = false)
	private String name;
	
	@Column(name = "description", length = 500)
	private String description;
}
