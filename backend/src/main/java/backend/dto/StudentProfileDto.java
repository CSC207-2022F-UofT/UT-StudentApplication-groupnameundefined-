package backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentProfileDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("program")
    private String program;

    @JsonProperty("college")
    private String college;

    @JsonProperty("enrolmentYear")
    private Integer enrolmentYear;

    @JsonProperty("timetable")
    private TimetableDto timetable;

    @JsonProperty("socialMediaProfile")
    private SocialMediaProfileDto socialMediaProfile;

}
