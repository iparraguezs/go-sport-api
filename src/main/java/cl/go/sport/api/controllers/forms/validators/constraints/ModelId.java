package cl.go.sport.api.controllers.forms.validators.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.springframework.data.repository.CrudRepository;

import cl.go.sport.api.controllers.forms.validators.ModelIdValidator;

/**
 * 
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ModelIdValidator.class)
@Documented
public @interface ModelId {
    String message() default "{constraints.id}";
    Class<? extends CrudRepository<?, Integer>> repository();
    boolean required() default true;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
