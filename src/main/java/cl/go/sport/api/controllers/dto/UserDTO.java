package cl.go.sport.api.controllers.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper=true)
public class UserDTO extends AbstractDTO {
	private String username;
	private String email;
	private List<RoleDTO> roles;
}
