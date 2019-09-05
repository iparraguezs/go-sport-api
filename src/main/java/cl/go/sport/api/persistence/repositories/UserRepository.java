package cl.go.sport.api.persistence.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import cl.go.sport.api.persistence.model.User;

public interface UserRepository extends PagingAndSortingRepository<User, Integer>, JpaSpecificationExecutor<User>
{
	Optional<User> findByUsername(String username);
	Optional<User> findByEmail(String email);
}
		