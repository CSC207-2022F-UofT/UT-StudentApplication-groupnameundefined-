package frontend.schema;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Getter
@Setter
@Component
public class FriendRequestSchema extends RequestSchema{

    public FriendRequestSchema() {
    }
}
