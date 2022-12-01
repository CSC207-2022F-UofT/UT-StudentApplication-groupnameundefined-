package frontend.exception;

import frontend.schema.APIExceptionSchema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClientException;

import java.time.ZonedDateTime;

@Getter
@Setter
public class APIException extends Exception {

	private String message;
	private String code;
	private HttpStatus httpStatus;
	private ZonedDateTime timeStamp;

	public APIException(APIExceptionSchema apiExceptionSchema) {
		this.code = apiExceptionSchema.getCode();
		this.httpStatus = apiExceptionSchema.getHttpStatus();
		this.message = apiExceptionSchema.getMessage();
		this.timeStamp = apiExceptionSchema.getTimestamp();
	}

}
