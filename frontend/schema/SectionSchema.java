package frontend.schema;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@Component
public class SectionSchema {
    private Long id;
    private String name;
    private CourseSchema course;
    public SectionSchema() {

    }
}