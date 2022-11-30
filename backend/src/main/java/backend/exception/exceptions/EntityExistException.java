package backend.exception.exceptions;

import backend.exception.APIException;
import org.springframework.http.HttpStatus;

public class EntityExistException extends APIException {
	public EntityExistException(String message, Class<?> entityClass) {
		super(message, "EXIST-" + entityClass.getSimpleName().toUpperCase(), HttpStatus.NOT_FOUND);
	}
}
