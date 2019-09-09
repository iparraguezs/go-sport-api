package cl.go.sport.api.controllers.forms;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

	@NotNull(message = "{userForm.username.notNull}")
	@Size(min = 4, max = 30, message = "{userForm.username.size}")
	@Pattern(regexp = "[a-zA-Z0-9]\\w+", message = "{userForm.username.pattern}")
	@UniqueKey(repository = UserRepository.class, column = "username", message = "{userForm.username.uniqueKey}")
	private String username;

	@NotNull(message = "{userForm.password.notNull}")
	@Size(min = 6, max = 15, message = "{userForm.password.size}")
	private String password;

	@NotNull(message = "{userForm.email.notNull}")
	@Email(message = "{userForm.email.email}")
	@Size(min = 4, max = 100, message = "{userForm.email.size}")
	@UniqueKey(repository = UserRepository.class, column = "email", message = "{userForm.email.uniqueKey}")
	private String email;
}
