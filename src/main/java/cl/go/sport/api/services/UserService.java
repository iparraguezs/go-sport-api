package cl.go.sport.api.services;

import java.util.List;

import cl.go.sport.api.controllers.forms.UserForm;
import cl.go.sport.api.persistence.model.User;
import cl.go.sport.api.services.results.ServiceResult;

public interface UserService extends BaseService {
	public ServiceResult<User> findByUsername(String username);
	public List<User> list();
	public User findById(Integer id);
	public User save(UserForm form);
	public User save(User user, UserForm form);
	public void delete(User user);
}
