package backend.exception.exceptions;

import backend.exception.APIException;
import org.springframework.http.HttpStatus;

public class CustomException extends APIException {
    public CustomException(String message, String code, HttpStatus httpStatus) {
        super(message, code, httpStatus);
    }
}
