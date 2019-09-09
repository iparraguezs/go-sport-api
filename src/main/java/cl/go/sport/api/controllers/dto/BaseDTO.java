package cl.go.sport.api.controllers.dto;

import java.util.Date;

import cl.go.sport.api.persistence.model.EntityBase;

public interface BaseDTO<E extends EntityBase> {

	Integer getPk();
	Date getCreatedAt();
	Date getUpdatedAt();
	Date getEnabledAt();
	Date getDisabledAt();
	Date getExpiredAt();
	Date getLockedAt();
	
	void setPk(Integer pk);
	void setCreatedAt(Date date);
	void setUpdatedAt(Date date);
	void setEnabledAt(Date date);
	void setDisabledAt(Date date);
	void setExpiredAt(Date date);
	void setLockedAt(Date date);
}
