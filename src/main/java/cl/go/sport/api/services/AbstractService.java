package cl.go.sport.api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;

import cl.go.sport.api.GoSportComponent;
import cl.go.sport.api.services.BaseService.Status;
import cl.go.sport.api.services.results.ServiceResult;
import cl.go.sport.api.utils.MessageUtils;

public class AbstractService extends GoSportComponent {
	@Autowired
	protected MessageUtils messageUtils;
	
	@Autowired
	protected ApplicationEventPublisher eventPublisher;
	
	@Value("${app.default.pagination.page-number}")
	protected Integer defaultPageNumber;
	
	@Value("${app.default.pagination.page-size}")
	protected Integer defaultPageSize;
	
	@Value("${app.default.pagination.sorted-by.user")
	protected String defaultSortedBy;

	protected <T> ServiceResult<T> createServiceResult(Optional<T> optional, String messageOk, String messageNok) {
		T result = null;
		String message = null;
		Status status = null;
		
		if(optional.isPresent()) {
			result = optional.get();
			message = messageOk;
			status = Status.OK;
		} else {
			message = messageNok;
			status = Status.NOK;
		}
		
		ServiceResult<T> serviceResult = new ServiceResult<>();
		serviceResult.setMessage(message);
		serviceResult.setResult(result);
		serviceResult.setStatus(status);
		return serviceResult;
	}
}
