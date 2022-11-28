package backend.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import backend.form.StudentProfileForm.CreateStudentProfileForm;
import backend.model.StudentProfile;

public interface StudentProfileService {

    StudentProfile createStudentProfile(CreateStudentProfileForm input);

    StudentProfile getStudentProfile(Long id);

    List<StudentProfile> getAllStudentProfiles();

    // StudentProfile updateStudentProfile(StudentProfile studentProfile);

    // void deleteStudentProfile(StudentProfile studentProfile);

}
