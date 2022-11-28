package backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean("logger")
    public Logger getLogger() {
        return LoggerFactory.getLogger(Logger.class);
    }

}
