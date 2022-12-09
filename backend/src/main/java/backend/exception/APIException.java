package backend.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * General APIException formatting
 */
@Getter
@Setter
public class APIException extends RuntimeException {

	private String code;
	private HttpStatus httpStatus;

	public APIException(Throwable e, String code, HttpStatus httpStatus) {
		super(e);
		this.code = code;
		this.httpStatus = httpStatus;
	}

	public APIException(String message, String code, HttpStatus httpStatus) {
		super(message);
		this.code = code;
		this.httpStatus = httpStatus;
	}

}
