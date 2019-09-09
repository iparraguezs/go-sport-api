package cl.go.sport.api.controllers.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import cl.go.sport.api.persistence.model.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@JsonInclude(Include.NON_NULL)
public class RoleDTO extends SuperBaseDTO<Role> {
	private String code;
	private String name;
	private String description;
	private List<FunctionDTO> functions;
}
