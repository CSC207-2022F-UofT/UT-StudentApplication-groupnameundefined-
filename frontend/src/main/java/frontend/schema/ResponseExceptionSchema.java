package frontend.schema;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ResponseExceptionSchema {
	private String message;
	private List<Map<String, String>> errors;
	private String code;
	private ZonedDateTime timestamp;
	private HttpStatus httpStatus;

	public ResponseExceptionSchema(
			String message,
			String code,
			ZonedDateTime timestamp,
			HttpStatus httpStatus
	) {
		this.message = message;
		this.code = code;
		this.timestamp = timestamp;
		this.httpStatus = httpStatus;
	}

	public ResponseExceptionSchema() {
	}

	public ResponseExceptionSchema(
			List<Map<String, String>> errors,
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
