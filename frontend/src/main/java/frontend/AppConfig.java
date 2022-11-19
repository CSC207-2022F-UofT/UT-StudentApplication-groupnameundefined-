package frontend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class AppConfig {
    @Bean("webClient")
    public WebClient getWebClient() {
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory("http://localhost:8080/api");
        return WebClient.builder().uriBuilderFactory(factory).build();
    }

    @Bean("logger")
    public Logger getLogger() {
        return LoggerFactory.getLogger(Logger.class);
    }
}
