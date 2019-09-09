package cl.go.sport.api.persistence.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import cl.go.sport.api.persistence.model.Role;

public interface RoleRepository extends PagingAndSortingRepository<Role, Integer>, JpaSpecificationExecutor<Role>
{

	Optional<Role> findByName(String name);
	boolean existsByCode(String code);
	boolean existsByName(String name);

}
		