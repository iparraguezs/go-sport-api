package cl.go.sport.api.persistence.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cl.go.sport.api.utils.DateUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
public abstract class EntityBase implements Serializable {

	private static final long serialVersionUID = -879358140693474742L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	@JsonIgnore
	private Integer id;

	@Column(name = "created_at", updatable = false, nullable = false)
	@CreationTimestamp
	private Date createdAt;

	@Column(name = "updated_at", nullable = false)
	@UpdateTimestamp
	private Date updatedAt;

	@Column(name = "enabled_at")
	private Date enabledAt;

	@Column(name = "disabled_at")
	private Date disabledAt;
	
	@Column(name = "expired_at")
	private Date expiredAt;
	
	@Column(name = "locked_at")
	private Date lockedAt;

	public boolean isEnabled() {
		return this.enabledAt != null && DateUtils.afterInclusive(DateUtils.toTimestamp(DateUtils.today()), this.enabledAt);
	}
	
	public boolean isDisabled() {
		return this.disabledAt != null &&  DateUtils.afterInclusive(DateUtils.toTimestamp(DateUtils.today()), this.disabledAt);
	}
	
	public boolean isExpired() {
		return this.expiredAt != null && DateUtils.afterInclusive(DateUtils.toTimestamp(DateUtils.today()), this.expiredAt);
	}
	
	public boolean isLocked() {
		return this.lockedAt != null && DateUtils.afterInclusive(DateUtils.toTimestamp(DateUtils.today()), this.lockedAt);
	}
}
