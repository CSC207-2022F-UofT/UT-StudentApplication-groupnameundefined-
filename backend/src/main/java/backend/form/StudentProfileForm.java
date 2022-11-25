package backend.form;

import java.io.File;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import backend.model.StudentProfile;

public class StudentProfileForm {
    public class CreateStudentProfileForm {
        private StudentProfile studentProfile;

        @Size(max = 20, message = "User ID too long")
        private Long userId;
        private File schedule;

        public StudentProfile getStudentProfile() {
            return studentProfile;
        }

        public Long getUserId() {
            return userId;
        }

        public File getSchedule() {
            return schedule;
        }
    }
}
