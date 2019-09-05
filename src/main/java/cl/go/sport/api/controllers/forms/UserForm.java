package cl.go.sport.api.controllers.forms;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import cl.go.sport.api.controllers.forms.validators.constraints.UniqueKey;
import cl.go.sport.api.persistence.model.User;
import cl.go.sport.api.persistence.repositories.UserRepository;
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
public class UserForm extends EntityForm<User> {
	private static final long serialVersionUID = -5731260521355518321L;

	@Size(min = 4, max = 30)
	@Pattern(regexp = "[a-zA-Z0-9]\\w+")
	@UniqueKey(repository = UserRepository.class, column = "username")
	private String username;

	@Size(min = 6, max = 15)
	private String password;

	@NotEmpty
	@Email
	@Size(max = 100)
	@UniqueKey(repository = UserRepository.class, column = "email")
	private String email;
}
