package backend.exception.exceptions;

import backend.exception.APIException;
import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends APIException {
	public InvalidCredentialsException(String message) {
		super(message, "INVALID_CREDENTIALS", HttpStatus.UNAUTHORIZED);
	}
}
