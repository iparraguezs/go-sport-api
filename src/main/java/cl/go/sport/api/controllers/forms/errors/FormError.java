package cl.go.sport.api.controllers.forms.errors;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FormError {
	private String field;
	private List<String> messages;
}
