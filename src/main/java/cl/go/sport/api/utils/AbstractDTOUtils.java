package cl.go.sport.api.utils;

import java.util.List;
import java.util.stream.Collectors;

import cl.go.sport.api.controllers.dto.BaseDTO;
import cl.go.sport.api.persistence.model.EntityBase;

/**
 * 
 * @author carlos
 *
 * @param <E>
 * @param <DTO>
 * @return 
 */
public abstract class AbstractDTOUtils <E extends EntityBase, DTO extends BaseDTO<E>> {
	
	/**
	 * 
	 * @param entity
	 * @return
	 */
	public abstract DTO full(E entity);
	
	/**
	 * 
	 * @param entity
	 * @return
	 */
	 public abstract DTO forList(E entity);

	/**
	 * @see #full(EntityBase)
	 * @param entities
	 * @return
	 */
	public final List<DTO> fullList(List<E> entities) {
		return entities.stream()
			.map(entity -> full(entity))
			.collect(Collectors.toList());
	}

	/**
	 * @see #forList(EntityBase)
	 * @param entities
	 * @return
	 */
	public final List<DTO> list(List<E> entities) {
		return entities.stream()
			.map(entity -> forList(entity))
			.collect(Collectors.toList());
	}
	
	/**
	 * 
	 * @param dto
	 * @param entity
	 * @return
	 */
	public final DTO common(DTO dto, E entity) {
		dto.setPk(entity.getId());
		dto.setCreatedAt(entity.getCreatedAt());
		dto.setDisabledAt(entity.getDisabledAt());
		dto.setEnabledAt(entity.getEnabledAt());
		dto.setExpiredAt(entity.getExpiredAt());
		dto.setLockedAt(entity.getLockedAt());
		dto.setUpdatedAt(entity.getUpdatedAt());
		return dto;
	}
}
