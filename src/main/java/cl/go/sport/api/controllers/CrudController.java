package cl.go.sport.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import cl.go.sport.api.controllers.dto.BaseDTO;
import cl.go.sport.api.controllers.forms.EntityForm;
import cl.go.sport.api.controllers.responses.ResponseBase;
import cl.go.sport.api.persistence.model.EntityBase;

public interface CrudController<F extends EntityForm<T>, DTO extends BaseDTO<T>, T extends EntityBase> {
	ResponseEntity<ResponseBase<List<DTO>>> list();
	ResponseEntity<ResponseBase<List<DTO>>> search(@RequestParam String s);
	ResponseEntity<ResponseBase<Page<DTO>>> searchPage(@RequestParam String s
			, @RequestParam(required = false) Integer pageNumber
			, @RequestParam(required = false) Integer pageSize
			, @RequestParam(required = false) String sortingCriteria);
	ResponseEntity<ResponseBase<DTO>> get(@PathVariable("id") Integer id);
	ResponseEntity<ResponseBase<DTO>> post(@Valid @RequestBody F form, BindingResult br);
	ResponseEntity<ResponseBase<DTO>> put(@PathVariable("id") Integer id, @Valid @RequestBody F form, BindingResult br);
	ResponseEntity<ResponseBase<?>> delete(@PathVariable("id") Integer id);
}
