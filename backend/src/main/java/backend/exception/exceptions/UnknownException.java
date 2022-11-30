package backend.exception.exceptions;

import backend.exception.APIException;
import org.springframework.http.HttpStatus;

public class UnknownException extends APIException {
    public UnknownException() {
        super("An unkown error has occurred.", "UNKNOWN", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
