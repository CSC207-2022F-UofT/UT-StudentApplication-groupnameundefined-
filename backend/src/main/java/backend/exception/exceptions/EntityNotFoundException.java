package backend.exception.exceptions;

import backend.exception.APIException;
import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends APIException {
    public EntityNotFoundException(String message, Class<?> entityClass) {
        super(message, "NOTFOUND-" + entityClass.getSimpleName().toUpperCase(), HttpStatus.NOT_FOUND);
    }
}
