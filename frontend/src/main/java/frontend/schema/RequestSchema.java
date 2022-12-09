package frontend.schema;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Getter
@Setter
@Component
public class RequestSchema {
    private Long id;
    private String status;
    private UserSchema from;
    private UserSchema to;
    private String message;
    private Timestamp timestamp;


    public RequestSchema() {

    }
}
