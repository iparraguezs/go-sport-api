package cl.go.sport.api.controllers.forms;

import javax.validation.constraints.Size;

import cl.go.sport.api.controllers.forms.validators.constraints.UniqueKey;
import cl.go.sport.api.persistence.model.Role;
import cl.go.sport.api.persistence.repositories.RoleRepository;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class RoleForm extends EntityForm<Role> {
	private static final long serialVersionUID = -5731260521355518321L;

	@Size(min = 4, max = 10)
	@UniqueKey(repository = RoleRepository.class, column = "code")
	private String code;
	
	@Size(min = 5, max = 50)
	@UniqueKey(repository = RoleRepository.class, column = "name")
	private String name;
	
	@Size(max = 500)
	private String description;
}
