package cl.go.sport.api.services;

import cl.go.sport.api.controllers.forms.UserForm;
import cl.go.sport.api.persistence.model.User;
import cl.go.sport.api.services.results.ServiceResult;

public interface UserService extends BaseService<User, UserForm> {
	public ServiceResult<User> findByUsername(String username);
}
