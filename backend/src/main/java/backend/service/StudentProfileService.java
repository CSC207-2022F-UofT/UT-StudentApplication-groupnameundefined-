package backend.service;

import java.util.*;

import backend.form.StudentProfileForm.*;
import backend.model.StudentProfile;
import org.springframework.web.multipart.MultipartFile;

public interface StudentProfileService {

	StudentProfile createStudentProfile(CreateStudentProfileForm input);

	StudentProfile loadCourseIcs(Long studentProfileId, MultipartFile iCalendar);

	List<StudentProfile> getAllStudentProfiles();

	StudentProfile getStudentProfileById(Long id);

	List<StudentProfile> matchStudentProfiles(MatchStudentProfileForm input);

	List<StudentProfile> matchStudentProfileByHabit(StudentProfile studentProfile);

	List<StudentProfile> matchStudentProfileByCourses(StudentProfile studentProfile);

	List<StudentProfile> sortStudentProfileByCourses(
			List<StudentProfile> studentProfiles,
			StudentProfile studentProfile
	);
}
