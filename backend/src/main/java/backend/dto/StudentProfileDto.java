package backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentProfileDto {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("program")
	private String program;

	@JsonProperty("college")
	private String college;

	@JsonProperty("enrolmentYear")
	private Integer enrolmentYear;

	@JsonProperty("socialMediaProfile")
	private SocialMediaProfileDto socialMediaProfile;

	@JsonProperty("habit")
	private HabitDto habit;

}
