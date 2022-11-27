package backend.form;

import java.io.File;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import backend.model.StudentProfile;

public class StudentProfileForm {

    public static class CreateStudentProfileForm {
        public CreateStudentProfileForm() {
        }

        private StudentProfile studentProfile;
        private Long userId;

        public StudentProfile getStudentProfile() {
            return studentProfile;
        }

        public Long getUserId() {
            return userId;
        }
    }

    public class updateStudentProfileForm {
        private StudentProfile studentProfile;
    }
}
