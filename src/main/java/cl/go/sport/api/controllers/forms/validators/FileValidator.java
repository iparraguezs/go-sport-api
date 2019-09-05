package cl.go.sport.api.controllers.forms.validators;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import cl.go.sport.api.controllers.forms.validators.constraints.File;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileValidator implements ConstraintValidator<File, MultipartFile> {
	String message;
    String[] mediaTypes;
    boolean required;
    
    @Override
    public void initialize(final File constraintAnnotation) {
        required = constraintAnnotation.required();
        message = constraintAnnotation.message();
        mediaTypes = constraintAnnotation.mediaTypes();
    }
    
	@Override
	public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
		boolean valid = true;
        try {
        	boolean isNull = Objects.isNull(file) || file.isEmpty();
			boolean requiredAndNull = required && isNull;
            boolean requiredAndNotNull = required && !isNull;
            boolean notRequiredAndNotNull = !required && !isNull;
            
            if(requiredAndNull) {
            	valid = false;
            } else if(requiredAndNotNull || notRequiredAndNotNull) {
            	
            	MediaType mediaType = null;
            	
            	try {
            		mediaType = MediaType.valueOf(file.getContentType());
            		for(String fileExtension: mediaTypes) {
            			MediaType mt = MediaType.valueOf(fileExtension);
            			valid = mt.equals(mediaType);
            			if(valid) break;
            		}
				} catch (Exception e) {
					valid = false;
				}
            }
            
            if (!valid) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(message)
                	.addConstraintViolation();
            }
        }
        catch (final IllegalArgumentException ignore) {
        	log.error("Falló la validación del archivo -> ", ignore);
        	valid = false;
        	message = String.format("Falló la validación del archivo");
        	context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
            	.addConstraintViolation();
        }
        
        return valid;
	}

}
