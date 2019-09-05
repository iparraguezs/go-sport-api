package cl.go.sport.api.controllers.forms.validators.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import cl.go.sport.api.controllers.forms.validators.RutValidator;

/**
 * 
 */
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RutValidator.class)
@Documented
public @interface Rut {
    String message() default "{constraints.rut}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
