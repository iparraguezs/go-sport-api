package cl.go.sport.api.utils;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import cl.go.sport.api.controllers.forms.errors.FormError;

public class FormUtils {
	private FormUtils() { }

	public static FormError transform(ObjectError error) {
		FormError formError = new FormError();
		formError.setField(error instanceof FieldError ? ((FieldError) error).getField() : "global");
		formError.setMessage(error.getDefaultMessage());
		return formError;
	}
}
