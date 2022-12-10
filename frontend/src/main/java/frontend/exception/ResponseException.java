package frontend.exception;

import frontend.schema.ResponseExceptionSchema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.util.annotation.Nullable;

import java.nio.charset.Charset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ResponseException extends Exception {

	private String message;
	private List<Map<String, String>> errors;
	private String code;
	private ZonedDateTime timestamp;
	private HttpStatus httpStatus;

	public ResponseException(ResponseExceptionSchema exceptionSchema) {
		this.message = exceptionSchema.getMessage();
		this.errors = exceptionSchema.getErrors();
		this.code = exceptionSchema.getCode();
		this.timestamp = exceptionSchema.getTimestamp();
		this.httpStatus = exceptionSchema.getHttpStatus();
	}

	public boolean hasFieldErrors() {
		return this.errors != null;
	}

	public boolean hasMessage() {
		return this.message != null;
	}

}
