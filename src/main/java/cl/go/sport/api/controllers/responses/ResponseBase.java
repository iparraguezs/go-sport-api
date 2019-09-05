package cl.go.sport.api.controllers.responses;

import java.sql.Timestamp;
import java.util.List;

import cl.go.sport.api.controllers.forms.errors.FormError;
import cl.go.sport.api.utils.DateUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseBase<T> {
	private int status;
	private String statusPhrase;
	private String globalMessageError;
	private List<FormError> formErrors;
	private T result;
	private Timestamp timestamp = DateUtils.toTimestamp(DateUtils.today());
}
