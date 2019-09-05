package cl.go.sport.api.controllers.forms.errors;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FormError {
	private String field;
	private String message;
}
