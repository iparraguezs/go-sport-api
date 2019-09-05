package cl.go.sport.api.controllers.forms.validators;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import cl.go.sport.api.controllers.forms.validators.constraints.UniqueKey;
import cl.go.sport.api.persistence.model.User;
import cl.go.sport.api.persistence.repositories.UserRepository;
import cl.go.sport.api.security.UserDetailsImpl;
import cl.go.sport.api.utils.BeanUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UniqueKeyValidator implements ConstraintValidator<UniqueKey, Object> {
	String message;
    Class<?> repository;
    boolean required;
    String column;
    
    private final static String METHOD_PREFFIX = "existsBy";
    
    @Override
    public void initialize(final UniqueKey constraintAnnotation) { 
		repository = constraintAnnotation.repository();
        required = constraintAnnotation.required();
        message = constraintAnnotation.message();
        column = constraintAnnotation.column();
    }
    
	@SuppressWarnings("unchecked")
	@Override
	public boolean isValid(Object fieldValue, ConstraintValidatorContext context) {
		boolean valid = true;
        try {
            boolean requiredAndNotNull = required && fieldValue != null;
            boolean notRequiredAndNotNull = !required && fieldValue != null;
            boolean notRequiredAndNull = !required && fieldValue == null;
            
            CrudRepository<?, Integer> repo = (CrudRepository<?, Integer>) BeanUtil.getBean(repository);
            
            Method method = repository.getDeclaredMethod(StringUtils.join(METHOD_PREFFIX, StringUtils.capitalize(column)), fieldValue.getClass());
            
            valid = notRequiredAndNull || ((requiredAndNotNull ||  notRequiredAndNotNull) && !(boolean) method.invoke(repo, fieldValue));
            
            if(!valid && repo instanceof UserRepository) {
            	String methodNameLower = column.toLowerCase();
				boolean containsUsername = methodNameLower.contains("username".toLowerCase());
				boolean containsEmail = methodNameLower.contains("email".toLowerCase());
				if(containsUsername || containsEmail) {
					Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	            	if(!Objects.isNull(authentication) && authentication.isAuthenticated()) {
	            		User user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
						if(containsUsername) {
	            			valid = user.getUsername().equals(fieldValue.toString());
	            		} else  if(containsEmail) {
							valid = user.getEmail().equals(fieldValue.toString());
						}
	            	}
				}
            }
            
            if (!valid) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(message)
                	.addConstraintViolation();
            }
        }
        catch (final IllegalArgumentException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ignore) {
        	log.error("Falló la validación de llave única -> ", ignore);
        	valid = false;
        	message = String.format("Falló la validación de llave única");
        	context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
            	.addConstraintViolation();
        }
        
        return valid;
	}

}
