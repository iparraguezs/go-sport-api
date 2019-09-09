package cl.go.sport.api.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cl.go.sport.api.controllers.dto.RoleDTO;
import cl.go.sport.api.persistence.model.Role;

@Component
public class RoleUtils extends AbstractDTOUtils<Role, RoleDTO>{

	@Autowired
	private FunctionUtils functionUtils;
	
	@Override
	public RoleDTO full(Role entity) {
		RoleDTO dto = RoleDTO.builder()
			.code(entity.getCode())
			.name(entity.getName())
			.description(entity.getDescription())
			.functions(functionUtils.list(entity.getFunctions()))
			.build();
		return common(dto, entity);
	}

	@Override
	public RoleDTO forList(Role entity) {
		return RoleDTO.builder()
			.pk(entity.getId())
			.name(entity.getName())
			.build();
	}
}
