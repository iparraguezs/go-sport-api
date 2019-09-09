package cl.go.sport.api.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import cl.go.sport.api.GoSportComponent;
import cl.go.sport.api.controllers.forms.errors.FormError;
import cl.go.sport.api.controllers.responses.ResponseBase;
import cl.go.sport.api.utils.DateUtils;
import cl.go.sport.api.utils.FormUtils;

public class AbstractWebController extends GoSportComponent {

	@Value("${app.api.crud.list}")
	protected String listPath;
	
	@Value("${app.api.crud.get}")
	protected String getPath;
	
	@Value("${app.api.crud.post}")
	protected String postPath;
	
	@Value("${app.api.crud.put}")
	protected String putPath;
	
	@Value("${app.api.crud.delete}")
	protected String deletePath;

	protected <T> ResponseBase<T> createResponseBaseOk(T result, String message, String subPath) {
		return createResponseBase(result, HttpStatus.OK, message, null, null, subPath);
	}
	
	protected <T> ResponseBase<T> createResponseBaseCreated(T result, String message, String subPath) {
		return createResponseBase(result, HttpStatus.CREATED, message, null, null, subPath);
	}
	
	protected <T> ResponseBase<T> createResponseBaseNoContent(T result, String message, String subPath) {
		return createResponseBase(result, HttpStatus.NO_CONTENT, message, null, null, subPath);
	}
	
	protected <T> ResponseBase<T> createResponseBaseNotFound(String errorMessage, String subPath) {
		return createResponseBase(null, HttpStatus.NOT_FOUND, null, errorMessage, null, subPath);
	}
	
	protected <T> ResponseBase<T> createResponseBaseBadRequest(T result, String errorMessage, String subPath) {
		return createResponseBase(result, HttpStatus.BAD_REQUEST, null, errorMessage, null, subPath);
	}
	
	protected <T> ResponseBase<T> createResponseBaseBadRequest(T result, String errorMessage, List<FormError> formErrors, String subPath) {
		return createResponseBase(result, HttpStatus.BAD_REQUEST, null, errorMessage, formErrors, subPath);
	}
	
	protected <T> ResponseBase<T> createResponseBase(T result, HttpStatus status, String message, String errorMessage, List<FormError> formErrors, String subPath) {
		return ResponseBase.<T>builder()
				.result(result)
				.status(status.value())
				.message(message)
				.errorMessage(errorMessage)
				.formErrors(formErrors)
				.path(contextPath.concat(subPath))
				.date(DateUtils.today(DateUtils.DD_MM_YYYY_HH_MM_SS))
				.build();
	}
	
	protected List<FormError> getErrors(BindingResult br) {
		return br
				.getAllErrors()
				.stream()
				.map(FormUtils::transform)
				.collect(Collectors.toList());
	}
}
