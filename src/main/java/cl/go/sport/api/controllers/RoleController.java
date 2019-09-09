package cl.go.sport.api.controllers;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Joiner;

import cl.go.sport.api.controllers.dto.RoleDTO;
import cl.go.sport.api.controllers.forms.RoleForm;
import cl.go.sport.api.controllers.responses.ResponseBase;
import cl.go.sport.api.controllers.specifications.SearchOperation;
import cl.go.sport.api.controllers.specifications.builder.UserSpecificationsBuilder;
import cl.go.sport.api.persistence.model.Role;
import cl.go.sport.api.persistence.model.User;
import cl.go.sport.api.services.RoleService;
import cl.go.sport.api.utils.RoleUtils;

@RestController
@RequestMapping("${app.api.role}")
public class RoleController extends AbstractWebController implements CrudController<RoleForm, RoleDTO, Role> {
	
	@Value("${app.api.role}")
	private String controllerPath;
	
	@Autowired
	private RoleService roleService;

	@Autowired
	private RoleUtils roleUtils;
	
	@Override
	@GetMapping("${app.api.crud.list}")
	public ResponseEntity<ResponseBase<List<RoleDTO>>> list(){
		return ResponseEntity.ok(createResponseBaseOk(
				roleService.list()
					.stream()
					.map(role -> roleUtils.forList(role))
					.collect(Collectors.toList())
				, getMessage("roleController.list.ok")
				, controllerPath.concat(listPath))
		);
	}
	
	@Override
	@GetMapping("${app.api.crud.get}/{id}")
	public ResponseEntity<ResponseBase<RoleDTO>> get(@PathVariable("id") Integer id){
		ResponseEntity<ResponseBase<RoleDTO>> response = null;
		Role role = roleService.findById(id);
		if(role != null) {
			response = ResponseEntity.ok(createResponseBaseOk(
					roleUtils.full(role)
					, getMessage("roleController.get.ok")
					, controllerPath.concat(getPath))
			);
		} else {
			response = new ResponseEntity<>(createResponseBaseNotFound(
					getMessage("roleController.get.notFound")
					, controllerPath.concat(getPath))
					, HttpStatus.NOT_FOUND);
		}
		return response;
	}
	
	@Override
	@PostMapping("${app.api.crud.post}")
	public ResponseEntity<ResponseBase<RoleDTO>> post(@Valid @RequestBody RoleForm form, BindingResult br) {
		ResponseEntity<ResponseBase<RoleDTO>> response = null;
		if (!br.hasErrors()) {
			Role role = roleService.save(form);
			response = new ResponseEntity<>(createResponseBaseCreated(
					roleUtils.full(role)
					, getMessage("roleController.post.ok")
					, controllerPath.concat(postPath))
					, HttpStatus.CREATED);
		} else {
			response = new ResponseEntity<>(createResponseBaseBadRequest(
					null, 
					getMessage("roleController.post.formErrors")
					, getErrors(br)
					, controllerPath.concat(postPath))
					, HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@Override
	@PutMapping("${app.api.crud.put}/{id}")
	public ResponseEntity<ResponseBase<RoleDTO>> put(@PathVariable("id") Integer id, @Valid @RequestBody RoleForm form, BindingResult br){
		ResponseEntity<ResponseBase<RoleDTO>> response = null;
		if (!br.hasErrors()) {
			Role role = roleService.findById(id);
			if (role != null) {
				role = roleService.save(role, form);
				response = new ResponseEntity<>(createResponseBaseNoContent(
						roleUtils.full(role)
						, getMessage("roleController.put.ok")
						, controllerPath.concat(putPath))
						, HttpStatus.NO_CONTENT);
			} else {
				role = roleService.save(form);
				response = new ResponseEntity<>(createResponseBaseCreated(
						roleUtils.full(role)
						, getMessage("userController.put.created")
						, controllerPath.concat(postPath))
						, HttpStatus.CREATED);
			} 
		}
		return response;
	}

	@Override
	@DeleteMapping("${app.api.crud.delete}/{id}")
	public ResponseEntity<ResponseBase<?>> delete(@PathVariable("id") Integer id) {
		ResponseEntity<ResponseBase<?>> response = null;
		Role role = roleService.findById(id);
		if(role != null) {
			roleService.delete(role);
			response = ResponseEntity.ok(createResponseBaseOk(
					roleUtils.full(role)
					, getMessage("userController.delete.ok")
					, controllerPath.concat(deletePath)));
		} else {
			response = new ResponseEntity<>(createResponseBaseNotFound(
					getMessage("roleController.delete.notFound")
					, controllerPath.concat(deletePath))
					, HttpStatus.NOT_FOUND);
		}
		return response;
	}

	@Override
	@GetMapping("${app.api.crud.search}")
	public ResponseEntity<ResponseBase<List<RoleDTO>>> search(@RequestParam String s) {
		UserSpecificationsBuilder builder = new UserSpecificationsBuilder();
		String operationSetExper = Joiner.on("|").join(SearchOperation.SIMPLE_OPERATION_SET);
		Pattern pattern = Pattern.compile("(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
		Matcher matcher = pattern.matcher(s + ",");
		while (matcher.find()) {
			builder.with(
				matcher.group(1), 
				matcher.group(2), 
				matcher.group(4), 
				matcher.group(3), 
				matcher.group(5));
		}

		Specification<User> spec = builder.build();
		return null;
	}

	@Override
	@GetMapping("${app.api.crud.search-page}")
	public ResponseEntity<ResponseBase<Page<RoleDTO>>> searchPage(@RequestParam String s
			, @RequestParam(required = false) Integer pageNumber
			, @RequestParam(required = false) Integer pageSize
			, @RequestParam(required = false) String sortingCriteria) {
		// TODO Auto-generated method stub
		return null;
	}
}
