package backend.service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import backend.form.StudentProfileForm.CreateStudentProfileForm;
import backend.model.StudentProfile;

public interface StudentProfileService {

	StudentProfile createStudentProfile(CreateStudentProfileForm input);

	List<StudentProfile> getAllStudentProfiles();

	StudentProfile getStudentProfileById(Long id);

	LinkedHashSet<StudentProfile> matchStudentProfileByHabit(Long id);

	List<StudentProfile> matchStudentProfileByCourses(Long id);
}
