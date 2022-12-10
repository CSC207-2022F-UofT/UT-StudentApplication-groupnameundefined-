package backend.exception;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

/**
 * Processes any throw exception from Controller-Service-Entity to readable format.
 */
@ControllerAdvice
public class APIExceptionHandler {

	private final Logger logger;

	@Autowired
	public APIExceptionHandler(Logger logger) {
		this.logger = logger;
	}

	@ExceptionHandler(APIException.class)
	public ResponseEntity<APIExceptionPayload> handleApiException(APIException e) {
		String message = e.getMessage();
		String code = e.getCode();
		ZonedDateTime timestamp = ZonedDateTime.now();
		HttpStatus httpStatus = e.getHttpStatus();

		APIExceptionPayload payload = new APIExceptionPayload(message, code, timestamp, httpStatus);
		return new ResponseEntity<>(payload, httpStatus);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<APIExceptionPayload> handleUnknownException(Exception e) {
		logger.error("Uncaught Exception", e);
		String message = "An unknown error has occurred.";
		String code = "UNKNOWN";
		ZonedDateTime timestamp = ZonedDateTime.now();
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

		APIExceptionPayload payload = new APIExceptionPayload(message, code, timestamp, httpStatus);
		return new ResponseEntity<>(payload, httpStatus);
	}
}
