package backend.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
@Setter
public class APIExceptionPayload {
    private final String message;
    private final String code;
    private final ZonedDateTime timestamp;
    private final HttpStatus httpStatus;


    public APIExceptionPayload(String message, String code, ZonedDateTime timestamp, HttpStatus httpStatus) {
        this.message = message;
        this.code = code;
        this.timestamp = timestamp;
        this.httpStatus = httpStatus;
    }
}
