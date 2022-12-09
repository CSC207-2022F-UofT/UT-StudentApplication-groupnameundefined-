package backend.service;

import java.util.*;

import backend.form.StudentProfileForm.*;
import backend.model.StudentProfile;
import org.springframework.web.multipart.MultipartFile;

public interface StudentProfileService {

	/**
	 * @param input An input defined by CreateStudnetProfileForm
	 * @return The created StudentProfile
	 */
	StudentProfile createStudentProfile(CreateStudentProfileForm input);

	/**
	 * Loads and proccess the course calendar file exported from ACORN, then find and add section blocks
	 * to StudentProfile's timetable
	 *
	 * @param id        ID of the StudentProfile for which to load the section blocks into (its timetable)
	 * @param iCalendar The courseCalendar (ics) file exported from ACORN
	 * @return The updated StudentProfile
	 */
	StudentProfile loadCourseIcs(Long id, String session, MultipartFile iCalendar);

	/**
	 * @return All StudentProfiles from table student-profile
	 */
	List<StudentProfile> getAllStudentProfiles();

	/**
	 * @param id ID of the StudentProfile to find
	 * @return The StudentProfile with the given ID
	 */
	StudentProfile getStudentProfileById(Long id);

	/**
	 * @param id       ID of the target StudentProfile to match for
	 * @param criteria A string defined by HABIT|COURSE|BOTH
	 * @return List of StudentProfiles constituting first 20 of the matches
	 */
	List<StudentProfile> matchStudentProfiles(Long id, String criteria);

	/**
	 * @param studentProfile The StudentProfile for which to match for
	 * @return List of StudentProfiles sorted in the order of specified criteria
	 */
	List<StudentProfile> matchStudentProfilesByHabit(StudentProfile studentProfile);

	List<StudentProfile> matchStudentProfilesByCourses(StudentProfile studentProfile);

	List<StudentProfile> sortStudentProfilesByCourses(
			List<StudentProfile> studentProfiles,
			StudentProfile studentProfile,
			Boolean isSecondarySort
	);
}
