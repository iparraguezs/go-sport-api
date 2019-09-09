package cl.go.sport.api.controllers.dto;

import java.util.Date;

import cl.go.sport.api.persistence.model.EntityBase;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class SuperBaseDTO<E extends EntityBase> implements BaseDTO<E> {
	private Integer pk;
	private Date createdAt;
	private Date updatedAt;
	private Date enabledAt;
	private Date disabledAt;
	private Date expiredAt;
	private Date lockedAt;
}
