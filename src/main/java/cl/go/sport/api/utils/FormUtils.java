package cl.go.sport.api.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import cl.go.sport.api.controllers.forms.errors.FormError;

public class FormUtils {
	private FormUtils() { }

	public static FormError transform(ObjectError error) {
		FormError formError = new FormError();
		formError.setField(getErrorFieldName(error));
		List<String> list = new ArrayList<>();
		list.add(error.getDefaultMessage());
		formError.setMessages(list);
		return formError;
	}

	private static String getErrorFieldName(ObjectError error) {
		return error instanceof FieldError ? ((FieldError) error).getField() : "global";
	}
	
	public static List<FormError> transform(List<ObjectError> errors) {
		Map<String, FormError> temp = new HashMap<>();
		errors.forEach(error -> {
			String errorFieldName = getErrorFieldName(error);
			if(temp.containsKey(errorFieldName)){
				temp.get(errorFieldName).getMessages().add(error.getDefaultMessage());
			} else {
				temp.put(errorFieldName, transform(error));
			}
		});
		return temp.values().stream().collect(Collectors.toList());
	}
}
