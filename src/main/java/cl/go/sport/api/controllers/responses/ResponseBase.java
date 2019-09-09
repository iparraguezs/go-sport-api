package cl.go.sport.api.controllers.responses;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import cl.go.sport.api.controllers.forms.errors.FormError;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class ResponseBase<T> {
	private int status;
	private String message;
	private String errorMessage;
	private List<FormError> formErrors;
	private T result;
	private Date date;
	private String path;
	private String throwable;
}
