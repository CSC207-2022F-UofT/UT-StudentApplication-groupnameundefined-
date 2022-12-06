package backend.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class FieldExceptionPayload {

	private List<Map<String, Object>> errors;
	private final String code;
	private final ZonedDateTime timestamp;
	private final HttpStatus httpStatus;


	public FieldExceptionPayload(
			List<Map<String, Object>> errors,
			String code,
			ZonedDateTime timestamp,
			HttpStatus httpStatus
	) {
		this.errors = errors;
		this.code = code;
		this.timestamp = timestamp;
		this.httpStatus = httpStatus;
	}
}
