package cl.go.sport.api.controllers.forms.validators;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import cl.go.sport.api.controllers.forms.validators.constraints.Rut;

public class RutValidator implements ConstraintValidator<Rut, String> {
	private final String regex = "^[0-9]{7,8}-[0-9kK]{1}";
	private String message;

    @Override
    public void initialize(final Rut constraintAnnotation) {
        message = constraintAnnotation.message();
    }
    
	@Override
	public boolean isValid(String rut, ConstraintValidatorContext context) {
		boolean valid = false;
        try {
            if(Pattern.compile(regex).matcher(rut).matches()) {
            	String[] stringRut = rut.split("-");
            	valid = stringRut[1].toLowerCase().equals(dv(stringRut[0]));
            }
        }
        catch (final Exception ignore) {
        	valid = false;
        	message = String.format("Falló la validación del rut");
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
