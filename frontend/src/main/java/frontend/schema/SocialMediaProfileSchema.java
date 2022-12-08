package frontend.schema;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Getter
@Setter
@Component
public class SocialMediaProfileSchema {
    private Long id;
    private String instagramId;
    private String facebookId;
    public SocialMediaProfileSchema() {

    }
}