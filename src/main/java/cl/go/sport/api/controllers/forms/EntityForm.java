package cl.go.sport.api.controllers.forms;

import java.io.Serializable;

import cl.go.sport.api.persistence.model.EntityBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public abstract class EntityForm<T extends EntityBase> implements Serializable {
	private static final long serialVersionUID = -7143522747353211278L;
	protected Integer id;
}
