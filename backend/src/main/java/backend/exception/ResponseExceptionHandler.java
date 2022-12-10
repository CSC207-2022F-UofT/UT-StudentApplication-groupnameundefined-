package backend.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Map.entry;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ResponseExceptionHandler {

	/**
	 * Method argument not valid is thrown when input does not match form or any constraints on the parameter
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException e
	) {
		List<Map<String, Object>> errorList = e.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(fieldError -> Map.ofEntries(
						entry("field", fieldError.getField()),
						entry("message", Optional.ofNullable(fieldError.getDefaultMessage())
								.orElse("Invalid value")),
						entry("rejectedValue", Optional.ofNullable(fieldError.getRejectedValue())
								.orElse("Not Provided"))
				))
				.toList();

		String code = "INVALID_INPUT";
		ZonedDateTime timestamp = ZonedDateTime.now();
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

		FieldExceptionPayload payload = new FieldExceptionPayload(errorList, code, timestamp, httpStatus);
		return new ResponseEntity<>(payload, httpStatus);
	}
}
