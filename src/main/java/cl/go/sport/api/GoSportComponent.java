package cl.go.sport.api;

import org.springframework.beans.factory.annotation.Autowired;

import cl.go.sport.api.services.BaseService.Status;
import cl.go.sport.api.services.results.ServiceResult;
import cl.go.sport.api.utils.MessageUtils;

public abstract class GoSportComponent {
	
	@Autowired
	protected MessageUtils messageUtils;
	
	protected String getMessage(String code) {
		return messageUtils.getMessage(code);
	}
	
	protected <T> boolean isOk(ServiceResult<T> result) {
		return result.getStatus().equals(Status.OK);
	}
}
