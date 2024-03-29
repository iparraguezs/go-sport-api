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

import cl.go.sport.api.controllers.dto.UserDTO;
import cl.go.sport.api.controllers.forms.UserForm;
import cl.go.sport.api.controllers.responses.ResponseBase;
import cl.go.sport.api.controllers.specifications.SearchOperation;
import cl.go.sport.api.controllers.specifications.builder.UserSpecificationsBuilder;
import cl.go.sport.api.persistence.model.User;
import cl.go.sport.api.services.UserService;
import cl.go.sport.api.utils.UserUtils;

@RestController
@RequestMapping("${app.api.user}")
public class UserController extends AbstractWebController implements CrudController<UserForm, UserDTO, User> {
	
	@Value("${app.api.user}")
	private String controllerPath;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserUtils userUtils;
	
	@Override
	@GetMapping("${app.api.crud.list}")
	public ResponseEntity<ResponseBase<List<UserDTO>>> list(){
		return ResponseEntity.ok(createResponseBaseOk(
				userService.list()
					.stream()
					.map(user -> userUtils.forList(user))
					.collect(Collectors.toList())
				, getMessage("userController.list.ok")
				, controllerPath.concat(listPath))
		);
	}
	
	@Override
	@GetMapping("${app.api.crud.get}/{id}")
	public ResponseEntity<ResponseBase<UserDTO>> get(@PathVariable("id") Integer id){
		ResponseEntity<ResponseBase<UserDTO>> response = null;
		User user = userService.findById(id);
		if(user != null) {
			response = ResponseEntity.ok(createResponseBaseOk(
					userUtils.full(user)
					, getMessage("userController.get.ok")
					, controllerPath.concat(getPath))
			);
		} else {
			response = new ResponseEntity<>(createResponseBaseNotFound(
					getMessage("userController.get.notFound")
					, controllerPath.concat(getPath))
					, HttpStatus.NOT_FOUND);
		}
		return response;
	}
	
	@Override
	@PostMapping("${app.api.crud.post}")
	public ResponseEntity<ResponseBase<UserDTO>> post(@Valid @RequestBody UserForm form, BindingResult br) {
		ResponseEntity<ResponseBase<UserDTO>> response = null;
		if (!br.hasErrors()) {
			User user = userService.save(form);
			response = new ResponseEntity<>(createResponseBaseCreated(
					userUtils.full(user)
					, getMessage("userController.post.ok")
					, controllerPath.concat(postPath))
					, HttpStatus.CREATED);
		} else {
			response = new ResponseEntity<>(createResponseBaseBadRequest(
					null, 
					getMessage("userController.post.formErrors")
					, getErrors(br)
					, controllerPath.concat(postPath))
					, HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@Override
	@PutMapping("${app.api.crud.put}/{id}")
	public ResponseEntity<ResponseBase<UserDTO>> put(@PathVariable("id") Integer id, @Valid @RequestBody UserForm form, BindingResult br){
		ResponseEntity<ResponseBase<UserDTO>> response = null;
		if (!br.hasErrors()) {
			User user = userService.findById(id);
			if (user != null) {
				user = userService.save(user, form);
				response = new ResponseEntity<>(createResponseBaseNoContent(
						userUtils.full(user)
						, getMessage("userController.put.ok")
						, controllerPath.concat(putPath))
						, HttpStatus.NO_CONTENT);
			} else {
				user = userService.save(form);
				response = new ResponseEntity<>(createResponseBaseCreated(
						userUtils.full(user)
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
		User user = userService.findById(id);
		if(user != null) {
			userService.delete(user);
			response = ResponseEntity.ok(createResponseBaseOk(
					userUtils.full(user)
					, getMessage("userController.delete.ok")
					, controllerPath.concat(deletePath)));
		} else {
			response = new ResponseEntity<>(createResponseBaseNotFound(
					getMessage("userController.delete.notFound")
					, controllerPath.concat(deletePath))
					, HttpStatus.NOT_FOUND);
		}
		return response;
	}

	@Override
	@GetMapping("${app.api.crud.search}")
	public ResponseEntity<ResponseBase<List<UserDTO>>> search(@RequestParam String s) {
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
	public ResponseEntity<ResponseBase<Page<UserDTO>>> searchPage(@RequestParam String s
			, @RequestParam(required = false) Integer pageNumber
			, @RequestParam(required = false) Integer pageSize
			, @RequestParam(required = false) String sortingCriteria) {
		// TODO Auto-generated method stub
		return null;
	}
}
