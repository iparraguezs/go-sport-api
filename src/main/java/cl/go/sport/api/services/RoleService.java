package cl.go.sport.api.services;

import cl.go.sport.api.controllers.forms.RoleForm;
import cl.go.sport.api.persistence.model.Role;
import cl.go.sport.api.services.results.ServiceResult;

public interface RoleService extends BaseService<Role, RoleForm> {
	public ServiceResult<Role> findByName(String username);
}
