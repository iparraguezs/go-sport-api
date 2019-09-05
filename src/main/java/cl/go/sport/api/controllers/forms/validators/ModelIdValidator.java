package cl.go.sport.api.controllers.forms.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.data.repository.CrudRepository;

import cl.go.sport.api.controllers.forms.validators.constraints.ModelId;
import cl.go.sport.api.utils.BeanUtil;

public class ModelIdValidator implements ConstraintValidator<ModelId, Integer> {
	Class<? extends CrudRepository<?, Integer>> repository;
	boolean required;
	String message;
    @Override
    public void initialize(final ModelId constraintAnnotation) { 
    	repository = constraintAnnotation.repository();
        required = constraintAnnotation.required();
        message = constraintAnnotation.message();
    }
    
	@Override
	public boolean isValid(Integer fieldValue, ConstraintValidatorContext context) {
		boolean valid = true;
        try {
            boolean requiredAndNotNull = required && fieldValue != null;
            boolean notRequiredAndNotNull = !required && fieldValue != null;
            boolean notRequiredAndNull = !required && fieldValue == null;
            
            CrudRepository<?, Integer> repo = (CrudRepository<?, Integer>) BeanUtil.getBean(repository);
            
			valid = notRequiredAndNull || ((requiredAndNotNull || notRequiredAndNotNull) && repo.existsById(fieldValue));
            
            if (!valid) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(message)
                	.addConstraintViolation();
            }
        }
        catch (final IllegalArgumentException ignore) {
        	valid = false;
        	message = String.format("Falló la validación de indentificador");
        	context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
            	.addConstraintViolation();
        }
        
        return valid;
	}

}
