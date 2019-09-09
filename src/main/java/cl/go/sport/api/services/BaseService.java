package cl.go.sport.api.services;

import java.util.List;

import cl.go.sport.api.controllers.forms.EntityForm;
import cl.go.sport.api.persistence.model.EntityBase;

public interface BaseService<E extends EntityBase, F extends EntityForm<E>> {
	enum Status{
		OK, NOK
	}

	List<E> list();
	E findById(Integer id);
	E save(F form);
	E save(E user, F form);
	void delete(E user);
}
