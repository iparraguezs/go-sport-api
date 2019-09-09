package cl.go.sport.api.utils;

import org.springframework.stereotype.Component;

import cl.go.sport.api.controllers.dto.FunctionDTO;
import cl.go.sport.api.persistence.model.Function;

@Component
public class FunctionUtils extends AbstractDTOUtils<Function, FunctionDTO> {

	@Override
	public FunctionDTO full(Function entity) {
		FunctionDTO dto = FunctionDTO.builder()
				.code(entity.getCode())
				.name(entity.getName())
				.description(entity.getDescription())
				.build();
		return common(dto, entity);
	}

	@Override
	public FunctionDTO forList(Function entity) {
		return FunctionDTO.builder()
				.pk(entity.getId())
				.name(entity.getName())
				.build();
	}
}
