package frontend.schema;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
@Setter
public class APIExceptionSchema {

	private String message;
	private String code;
	private ZonedDateTime timestamp;
	private HttpStatus httpStatus;

}
