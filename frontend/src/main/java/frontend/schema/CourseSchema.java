package frontend.schema;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@Component
public class CourseSchema {
    private Long id;
    private String name;
    private String code;
    private String sectionCode;
    private String campus;
    public CourseSchema() {

    }
}
