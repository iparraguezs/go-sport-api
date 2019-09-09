package cl.go.sport.api.controllers.forms;

import java.io.Serializable;

import cl.go.sport.api.persistence.model.EntityBase;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public abstract class EntityForm<T extends EntityBase> implements Serializable {
	private static final long serialVersionUID = -7143522747353211278L;
	protected Integer id;
}
