package backend.form;

import java.io.File;
import java.util.List;

import javax.validation.constraints.*;

import backend.model.StudentProfile;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

public class StudentProfileForm {

	@Getter
	public static class CreateStudentProfileForm {

		private final Long userId;

		@Size(max = 256)
		private final String program;

		@Size(max = 256)
		private final String college;

		@Max(value = 9999)
		@Min(value = 1827)
		private final Integer enrolmentYear;

		public CreateStudentProfileForm(Long userId, String program, String college, Integer enrolmentYear) {
			this.userId = userId;
			this.program = program;
			this.college = college;
			this.enrolmentYear = enrolmentYear;
		}

	}

	@Getter
	public static class MatchStudentProfileForm {

		@NotNull
		private final Long studentProfileId;

		@Size(min = 1, max = 2)
		private final List<@NotEmpty @Pattern(regexp = "HABIT|COURSE") String> matchBy;

		public MatchStudentProfileForm(Long studentProfileId, List<String> matchBy) {
			this.studentProfileId = studentProfileId;
			this.matchBy = matchBy;
		}
	}
}
