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

	List<StudentProfile> matchStudentProfiles(Long id, String criteria);

	List<StudentProfile> matchStudentProfilesByHabit(StudentProfile studentProfile);

	List<StudentProfile> matchStudentProfilesByCourses(StudentProfile studentProfile);

	List<StudentProfile> sortStudentProfilesByCourses(
			List<StudentProfile> studentProfiles,
			StudentProfile studentProfile,
			Boolean isSecondarySort
	);
}
