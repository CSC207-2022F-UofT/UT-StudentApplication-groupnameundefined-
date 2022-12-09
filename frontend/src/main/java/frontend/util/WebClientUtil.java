package frontend.util;

import frontend.exception.ResponseException;
import frontend.schema.ResponseExceptionSchema;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import reactor.core.publisher.Mono;

public class WebClientUtil {

	public static ExchangeFilterFunction errorHandler() {
		return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
			HttpStatus httpStatus = clientResponse.statusCode();
			if (httpStatus.isError()) {
				return clientResponse
						.bodyToMono(ResponseExceptionSchema.class)
						.flatMap(exception -> Mono.error(new ResponseException(exception)));
			} else {
				return Mono.just(clientResponse);
			}
		});
	}
}
