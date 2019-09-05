package cl.go.sport.api.config.security.exceptions;

public class JwtAuthenticationException extends RuntimeException {

	private static final long serialVersionUID = -4704405244709618327L;

	public JwtAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
