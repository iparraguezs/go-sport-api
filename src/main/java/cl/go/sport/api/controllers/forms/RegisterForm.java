package cl.go.sport.api.controllers.forms;

import java.io.Serializable;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import cl.go.sport.api.controllers.forms.validators.constraints.FieldMatch;
import cl.go.sport.api.controllers.forms.validators.constraints.UniqueKey;
import cl.go.sport.api.persistence.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@FieldMatch(first = "password", second = "repeatPassword")
public class RegisterForm extends AbstractForm implements Serializable, RepeatPasswordForm {

	private static final long serialVersionUID = 8743858444156131890L;

	@Size(min = 4, max = 30)
	@Pattern(regexp = "[a-zA-Z0-9]\\w+")
	@UniqueKey(repository = UserRepository.class, column = "username")
	private String username;

	@NotEmpty
	@Email
	@Size(max = 100)
	@UniqueKey(repository = UserRepository.class, column = "email")
	private String email;

	@Size(min = 6, max = 15)
	private String password;

	@Size(min = 6, max = 15)
	private String repeatPassword;

	@AssertTrue
	private boolean tos;
}
