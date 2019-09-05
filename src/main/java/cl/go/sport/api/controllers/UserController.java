package cl.go.sport.api.controllers;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import cl.go.sport.api.controllers.forms.UserForm;
import cl.go.sport.api.controllers.responses.ResponseBase;
import cl.go.sport.api.controllers.specifications.SearchOperation;
import cl.go.sport.api.controllers.specifications.builder.UserSpecificationsBuilder;
import cl.go.sport.api.persistence.model.User;
import cl.go.sport.api.services.UserService;

@RestController
@RequestMapping("${app.api.user}")
public class UserController extends AbstractWebController implements CrudController<UserForm, User> {
	@Autowired
	private UserService userService;
	
	@Override
	@GetMapping("${app.api.crud.list}")
	public ResponseEntity<ResponseBase<List<User>>> list(){
		ResponseBase<List<User>> build = new ResponseBase<>();
		build.setResult(userService.list());
		build.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(build);
	}
	
	@Override
	@GetMapping("${app.api.crud.get}/{id}")
	public ResponseEntity<ResponseBase<User>> get(@PathVariable("id") Integer id){
		ResponseEntity<ResponseBase<User>> response = null;
		User user = userService.findById(id);
		if(user != null) {
			ResponseBase<User> build = new ResponseBase<>();
			build.setResult(user);
			build.setStatus(HttpStatus.OK.value());
			response = ResponseEntity.ok(build);
		} else {
			ResponseBase<User> build = new ResponseBase<>();
			build.setResult(null);
			build.setGlobalMessageError(getMessage("userController.get.notFound"));
			build.setStatus(HttpStatus.NOT_FOUND.value());
			response = new ResponseEntity<>(build, HttpStatus.NOT_FOUND);
		}
		return response;
	}
	
	@Override
	@PostMapping("${app.api.crud.post}")
	public ResponseEntity<ResponseBase<User>> post(@Valid @RequestBody UserForm form, BindingResult br) {
		ResponseEntity<ResponseBase<User>> response = null;
		if (!br.hasErrors()) {
			User user = userService.save(form);
			ResponseBase<User> build = new ResponseBase<>();
			build.setResult(user);
			build.setStatus(HttpStatus.CREATED.value());
			response = new ResponseEntity<>(build, HttpStatus.CREATED);
		} else {
			ResponseBase<User> build = new ResponseBase<>();
			build.setResult(null);
			build.setGlobalMessageError(getMessage("userController.post.formErrors"));
			build.setStatus(HttpStatus.BAD_REQUEST.value());
			response = new ResponseEntity<>(build, HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@Override
	@PutMapping("${app.api.crud.put}/{id}")
	public ResponseEntity<ResponseBase<User>> put(@PathVariable("id") Integer id, @Valid @RequestBody UserForm form, BindingResult br){
		ResponseEntity<ResponseBase<User>> response = null;
		if (!br.hasErrors()) {
			User user = userService.findById(id);
			if (user != null) {
				user = userService.save(user, form);
				ResponseBase<User> build = new ResponseBase<>();
				build.setResult(user);
				build.setStatus(HttpStatus.NO_CONTENT.value());
				response = new ResponseEntity<>(build, HttpStatus.NO_CONTENT);
			} else {
				user = userService.save(form);
				ResponseBase<User> build = new ResponseBase<>();
				build.setResult(user);
				build.setStatus(HttpStatus.CREATED.value());
				response = new ResponseEntity<>(build, HttpStatus.CREATED);
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
			ResponseBase<User> build = new ResponseBase<>();
			build.setResult(user);
			build.setStatus(HttpStatus.OK.value());
			response = ResponseEntity.ok(build);
		} else {
			ResponseBase<User> build = new ResponseBase<>();
			build.setResult(null);
			build.setGlobalMessageError(getMessage("userController.get.notFound"));
			build.setStatus(HttpStatus.NOT_FOUND.value());
			response = new ResponseEntity<>(build, HttpStatus.NOT_FOUND);
		}
		return response;
	}

	@Override
	@GetMapping("${app.api.crud.search}")
	public ResponseEntity<ResponseBase<List<User>>> search(@RequestParam String s) {
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
	public ResponseEntity<ResponseBase<Page<User>>> searchPage(@RequestParam String s
			, @RequestParam(required = false) Integer pageNumber
			, @RequestParam(required = false) Integer pageSize
			, @RequestParam(required = false) String sortingCriteria) {
		// TODO Auto-generated method stub
		return null;
	}
}
