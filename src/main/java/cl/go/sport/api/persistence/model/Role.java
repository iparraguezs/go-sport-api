package cl.go.sport.api.persistence.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	@OneToMany(mappedBy = "role")
	private List<Funcionality> functionalities = new ArrayList<>();
	
	public List<Function> getFunctions() {
		return getFunctionalities()
		.stream()
		.map(f -> f.getFunction())
		.collect(Collectors.toList());
	}
}
