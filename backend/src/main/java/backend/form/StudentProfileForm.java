package backend.form;

import java.io.File;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

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

	//    public class updateStudentProfileForm {
	//        private StudentProfile studentProfile;
	//    }
}
