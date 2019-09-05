package cl.go.sport.api.controllers.forms.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import cl.go.sport.api.controllers.forms.validators.constraints.EmptyOrSize;

public class EmptyOrSizeValidator implements ConstraintValidator<EmptyOrSize, String> {
	private String message;
	private int min;
	private int max;

    @Override
    public void initialize(final EmptyOrSize constraintAnnotation) {
        message = constraintAnnotation.message();
        min = constraintAnnotation.min();
        max = constraintAnnotation.max();
    }
    
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		boolean valid = true;
        try {
            if(!StringUtils.isEmpty(value)) {
            	int length = value.length();
            	valid = length >= min && length <= max;
            }
        }
        catch (final Exception ignore) {
        	valid = false;
        	message = String.format("Falló la validación EmptyOrSize");
        }

        if (!valid) {
            context.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }

        return valid;
	}
	
	public static String dv ( String rut ) {
		Integer M=0,S=1,T=Integer.parseInt(rut);
		for (;T!=0;T=(int) Math.floor(T/=10))
			S=(S+T%10*(9-M++%6))%11;
		return ( S > 0 ) ? String.valueOf(S-1) : "k";		
	}

}
