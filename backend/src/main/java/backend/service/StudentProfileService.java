package backend.service;

import java.util.Optional;

import backend.form.StudentProfileForm.CreateStudentProfileForm;
import backend.model.StudentProfile;

public interface StudentProfileService {

    StudentProfile createStudentProfile(CreateStudentProfileForm input);

    StudentProfile getStudentProfile(Long id);

    // StudentProfile updateStudentProfile(StudentProfile studentProfile);

    // void deleteStudentProfile(StudentProfile studentProfile);

}
