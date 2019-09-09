package cl.go.sport.api.controllers.forms.validators.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import cl.go.sport.api.controllers.forms.validators.UniqueKeyValidator;

/**
 * 
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueKeyValidator.class)
@Documented
public @interface UniqueKey {
    String message() default "";
    String column();
    Class<?> repository();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
	boolean withAuthenticatedUser() default false;
}
