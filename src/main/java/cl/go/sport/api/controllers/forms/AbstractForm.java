package cl.go.sport.api.controllers.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractForm {
	public enum Status {
		INFO, SUCCESS, WARNING, ERROR
	}
	
	protected String message;
	protected Status status;
}
