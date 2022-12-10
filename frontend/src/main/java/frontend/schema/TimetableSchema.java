package frontend.schema;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@Component
public class TimetableSchema {
    private Long id;
    private StudentProfileSchema studentProfile;
    private Set<BlockSchema> blocks;
    public TimetableSchema() {

    }
}