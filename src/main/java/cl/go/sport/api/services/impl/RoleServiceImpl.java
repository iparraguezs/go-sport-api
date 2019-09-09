package cl.go.sport.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.go.sport.api.controllers.forms.RoleForm;
import cl.go.sport.api.persistence.model.Role;
import cl.go.sport.api.persistence.repositories.RoleRepository;
import cl.go.sport.api.services.AbstractService;
import cl.go.sport.api.services.RoleService;
import cl.go.sport.api.services.results.ServiceResult;

@Service
public class RoleServiceImpl extends AbstractService implements RoleService {

	@Autowired
	private RoleRepository roleRepository;
		
	@Override
	@Transactional(readOnly = true)
	public  ServiceResult<Role> findByName(String name)
	{
		Optional<Role> userByUsername = roleRepository.findByName(name);
		ServiceResult<Role> result = new ServiceResult<>();

		Status status = userByUsername.isPresent() ? Status.OK : Status.NOK;
		String message = null;
		if (userByUsername.isPresent()) {
			result.setResult(userByUsername.get());
			message = String.format("Se ha encontrado el usuario '%s'", name);
		} else {
			message = String.format("El usuario '%s' no existe", name);;
		}

		result.setStatus(status);
		result.setMessage(message);
		return result;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Role> list() {
		return (List<Role>) roleRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Role findById(Integer id) {
		return roleRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Role save(RoleForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Role save(Role role, RoleForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void delete(Role role) {
		// TODO Auto-generated method stub
		
	}
}
