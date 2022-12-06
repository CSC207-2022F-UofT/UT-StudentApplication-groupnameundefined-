package backend.service.imp;

import java.util.*;

import backend.exception.exceptions.EntityNotFoundException;
import backend.model.*;
import backend.repository.HabitRepository;
import backend.repository.SectionBlockRepository;
import backend.service.TimetableService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.form.StudentProfileForm.CreateStudentProfileForm;
import backend.repository.StudentProfileRepository;
import backend.repository.UserRepository;
import backend.service.StudentProfileService;
;

@Service
public class StudentProfileServiceImp implements StudentProfileService {

	private final Logger logger;
	private final StudentProfileRepository studentProfileRepository;
	private final UserRepository userRepository;
	private final SectionBlockRepository sectionBlockRepository;
	private final TimetableService timetableService;

	private final HabitRepository habitRepository;

	@Autowired
	public StudentProfileServiceImp(
			Logger logger,
			StudentProfileRepository studentProfileRepository,
			UserRepository userRepository,
			SectionBlockRepository sectionBlockRepository,
			TimetableService timetableService,
			HabitRepository habitRepository
	) {
		this.logger = logger;
		this.studentProfileRepository = studentProfileRepository;
		this.userRepository = userRepository;
		this.sectionBlockRepository = sectionBlockRepository;
		this.timetableService = timetableService;
		this.habitRepository = habitRepository;
	}

	@Override
	public StudentProfile createStudentProfile(CreateStudentProfileForm input) {
		Optional<User> _user = userRepository.findById(input.getUserId());
		if (_user.isEmpty()) {
			throw new EntityNotFoundException(String.format("Unable to find user with id %d", input.getUserId()), User.class);
		}

		User user = _user.get();
		StudentProfile studentProfile = new StudentProfile(input.getProgram(), input.getCollege(), input.getEnrolmentYear());

		user.setStudentProfile(studentProfile);
		studentProfile.setUser(user);

		return userRepository.save(user).getStudentProfile();
	}

	@Override
	public List<StudentProfile> getAllStudentProfiles() {
		return studentProfileRepository.findAll();
	}

	@Override
	public StudentProfile getStudentProfileById(Long id) {
		Optional<StudentProfile> studentProfile = studentProfileRepository.findById(id);
		if (studentProfile.isPresent()) {
			return studentProfile.get();
		}

		throw new EntityNotFoundException(String.format("Unable to find student profile with id '%d'.", id), StudentProfile.class);
	}

	@Override
	public List<StudentProfile> matchStudentProfileByProperty(Long id) {
		List<StudentProfile> result = new ArrayList<StudentProfile>();
		List<StudentProfile> studentProfiles = studentProfileRepository.findAll();

		StudentProfile studentProfile = this.getStudentProfileById(id);
		Habit habit = studentProfile.getHabit();

		studentProfiles.sort(new Comparator<StudentProfile>() {
			@Override
			public int compare(StudentProfile o1, StudentProfile o2) {
				return getAbsoluteDistance(o1.getHabit(), habit) - getAbsoluteDistance(o2.getHabit(), habit);
			}
		});

		return studentProfiles.subList(0, 20);
	}

	@Override
	public List<StudentProfile> matchStudentProfileByCourse(Long id) {
		StudentProfile _studentProfile = this.getStudentProfileById(id);
		List<StudentProfile> studentProfiles = this.getAllStudentProfiles();

		Map<StudentProfile, Integer> courseCountMap = new HashMap<>();
		for (StudentProfile studentProfile : studentProfiles) {
			Integer courseMatchCount = 0;
			List<Course> joinedCourses = new ArrayList<>();
			joinedCourses.addAll(studentProfile.getCourses());
			joinedCourses.addAll(_studentProfile.getCourses());

			Set<Course> joinedCoursesSet = new HashSet<>(joinedCourses);
			for (Course course : joinedCoursesSet) {
				if (Collections.frequency(joinedCourses, course) > 1) {
					courseMatchCount += 1;
				}
			}

			courseCountMap.put(studentProfile, courseMatchCount);
		}
	}

	public int getAbsoluteDistance(Habit habit1, Habit habit2) {
		return (int) (Math.pow(habit1.getTalkative() - habit2.getTalkative(), 2) +
				Math.pow(habit1.getCollaborative() - habit2.getCollaborative(), 2));
	}

}
