package cl.go.sport.api.controllers.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import cl.go.sport.api.persistence.model.User;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@JsonInclude(Include.NON_NULL)
public class UserDTO extends SuperBaseDTO<User>  {
	private String username;
	private String email;
	private List<RoleDTO> roles;
}