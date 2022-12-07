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
}
