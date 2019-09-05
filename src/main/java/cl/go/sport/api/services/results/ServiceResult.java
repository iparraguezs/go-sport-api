package cl.go.sport.api.services.results;

import cl.go.sport.api.services.BaseService.Status;
import lombok.Data;

@Data
public class ServiceResult<T> {
	private Status status;
	private String message;
	private Throwable throwable;
	private T result;
}
