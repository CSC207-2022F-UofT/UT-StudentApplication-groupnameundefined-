package frontend.schema;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

public class FieldExceptionSchema {

	private List<Map<String, Object>> errors;
	private String code;
	private ZonedDateTime timestamp;
	private HttpStatus httpStatus;
	
}
