package cl.go.sport.api.controllers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import cl.go.sport.api.persistence.model.Function;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@JsonInclude(Include.NON_NULL)
public class FunctionDTO extends SuperBaseDTO<Function> {
	private String code;
	private String name;
	private String description;
}
