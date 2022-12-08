package frontend.schema;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Getter
@Setter
@Component
public class HabitSchema {
    private Long id;
    private Integer collaborative;
    private Integer talkative;

    public HabitSchema() {

    }
}
