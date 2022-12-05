package backend.exception.exceptions;

import backend.exception.APIException;
import org.springframework.http.HttpStatus;

public class BadRequestException extends APIException {
	public BadRequestException(String message) {
		super(message, "BAD_REQUEST", HttpStatus.BAD_REQUEST);
	}
}
