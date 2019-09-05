package cl.go.sport.api.controllers.forms.validators.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import cl.go.sport.api.controllers.forms.validators.FileValidator;

/**
 * 
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FileValidator.class)
@Documented
public @interface File {
    String message() default "{constraints.file}";
    boolean required() default true;
    String[] mediaTypes();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
