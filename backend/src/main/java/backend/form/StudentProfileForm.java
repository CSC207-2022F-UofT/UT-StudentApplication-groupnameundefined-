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

        private Long userId;

        @Size(max = 256)
        private String program;

        @Size(max = 256)
        private String college;

        @Max(value = 9999)
        @Min(value = 1827)
        private Integer enrolmentYear;
        
    }

    public class updateStudentProfileForm {
        private StudentProfile studentProfile;
    }
}
