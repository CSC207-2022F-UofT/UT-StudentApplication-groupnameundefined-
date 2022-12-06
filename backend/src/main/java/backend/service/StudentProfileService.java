package backend.service;

import java.util.*;

import backend.form.StudentProfileForm.CreateStudentProfileForm;
import backend.model.StudentProfile;

public interface StudentProfileService {

	StudentProfile createStudentProfile(CreateStudentProfileForm input);

	List<StudentProfile> getAllStudentProfiles();

	StudentProfile getStudentProfileById(Long id);

	List<StudentProfile> matchStudentProfileByHabit(Long id);

	List<StudentProfile> matchStudentProfileByCourses(Long id);
}
