package cl.go.sport.api.controllers.forms;

import javax.validation.constraints.NotNull;
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

	@NotNull(message = "{roleForm.code.notNull}")
	@Size(min = 4, max = 10, message = "{roleForm.code.size}")
	@UniqueKey(repository = RoleRepository.class, column = "code", message = "{roleForm.code.uniqueKey}")
	private String code;
	
	@NotNull(message = "{roleForm.name.notNull}")
	@Size(min = 5, max = 50, message = "{roleForm.name.size}")
	@UniqueKey(repository = RoleRepository.class, column = "name", message = "{roleForm.name.uniqueKey}")
	private String name;
	
	@Size(max = 500, message = "{roleForm.description.size}")
	private String description;
}
