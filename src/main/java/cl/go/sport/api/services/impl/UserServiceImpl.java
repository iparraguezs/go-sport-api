package cl.go.sport.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.go.sport.api.config.security.GoSportUserDetails;
import cl.go.sport.api.controllers.forms.UserForm;
import cl.go.sport.api.persistence.model.User;
import cl.go.sport.api.persistence.repositories.UserRepository;
import cl.go.sport.api.services.AbstractService;
import cl.go.sport.api.services.UserService;
import cl.go.sport.api.services.results.ServiceResult;

@Service
public class UserServiceImpl extends AbstractService implements UserDetailsService, UserService {

	@Autowired
	private UserRepository userRepository;
		
	@Override
	@Transactional(readOnly = true)
	public  ServiceResult<User> findByUsername(String username)
	{
		Optional<User> userByUsername = userRepository.findByUsername(username);
		ServiceResult<User> result = new ServiceResult<>();

		Status status = userByUsername.isPresent() ? Status.OK : Status.NOK;
		String message = null;
		if (userByUsername.isPresent()) {
			result.setResult(userByUsername.get());
			message = String.format("Se ha encontrado el usuario '%s'", username);
		} else {
			message = String.format("El usuario '%s' no existe", username);;
		}

		result.setStatus(status);
		result.setMessage(message);
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optionalUser = userRepository.findByUsername(username);
		if (!optionalUser.isPresent()) {
			throw new UsernameNotFoundException(String.format("No se encontró usuario para el username '%s'.", username));
		} else {
			return new GoSportUserDetails(optionalUser.get());
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<User> list() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public User findById(Integer id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public User save(UserForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public User save(User user, UserForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void delete(User user) {
		// TODO Auto-generated method stub
		
	}
}
