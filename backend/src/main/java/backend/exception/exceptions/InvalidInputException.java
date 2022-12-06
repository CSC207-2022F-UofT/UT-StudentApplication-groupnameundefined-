package backend.exception.exceptions;

import backend.exception.APIException;
import org.springframework.http.HttpStatus;

public class InvalidInputException extends APIException {
	public InvalidInputException(String message) {
		super(message, "INVALID_INPUT", HttpStatus.BAD_REQUEST);
	}
}
