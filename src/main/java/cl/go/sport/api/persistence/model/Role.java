package cl.go.sport.api.persistence.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "role")
public class Role extends EntityBase {
	private static final long serialVersionUID = 328155766745861459L;

	@Column(name = "code", length = 10, nullable = false, unique = true)
	private String code;
	
	@Column(name = "name", length = 50, nullable = false, unique = true)
	private String name;
	
	@Column(name = "description", length = 500)
	private String description;
	
	@Builder.Default
	@JsonIgnore
	@OneToMany(mappedBy = "role")
	private List<Funcionality> functions = new ArrayList<>();
}
