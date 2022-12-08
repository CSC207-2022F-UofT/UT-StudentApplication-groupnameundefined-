package frontend.schema;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Getter
@Setter
@Component
public class StudentProfileSchema {
    private Long id;
    private Long userId;
    private String program;
    private String college;
    private Integer enrolmentYear;
    private TimetableSchema timetable;
    private SocialMediaProfileSchema socialMediaProfile;
    private HabitSchema habit;
    public StudentProfileSchema() {

    }
}
