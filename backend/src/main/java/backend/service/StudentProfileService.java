package backend.service;

import backend.form.StudentProfileForm.CreateStudentProfileForm;
import backend.model.StudentProfile;

public interface StudentProfileService {
    StudentProfile createStudentProfile(CreateStudentProfileForm studentProfile);

    StudentProfile updateStudentProfile(StudentProfile studentProfile);

    void deleteStudentProfile(StudentProfile studentProfile);
}
