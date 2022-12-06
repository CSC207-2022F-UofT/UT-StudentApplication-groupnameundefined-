package backend.service.imp;

import java.util.*;
import java.util.stream.Collectors;

import backend.exception.exceptions.BadRequestException;
import backend.exception.exceptions.EntityNotFoundException;
import backend.model.*;
import backend.repository.HabitRepository;
import backend.repository.SectionBlockRepository;
import backend.service.TimetableService;
import org.apache.commons.lang3.tuple.Pair;
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
		Habit habit = new Habit();
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
	public List<StudentProfile> matchStudentProfileByHabit(Long id) {
		StudentProfile studentProfile = this.getStudentProfileById(id);
		Habit habit = studentProfile.getHabit();

		List<StudentProfile> studentProfiles = studentProfileRepository.findAll();
		studentProfiles.remove(studentProfile);

		logger.info(habit.getTalkative().toString());

		studentProfiles.sort((o1, o2) -> {
					Double d1 = getAbsoluteDistance(o1.getHabit(), habit);
					Double d2 = getAbsoluteDistance(o2.getHabit(), habit);
					if (d1 - d2 > 0) {
						return 1;
					} else if (d1.equals(d2)) {
						return 0;
					} else {
						return -1;
					}
				}
		);

		return studentProfiles;
	}

	@Override
	public List<StudentProfile> matchStudentProfileByCourses(Long id) {
		StudentProfile _studentProfile = this.getStudentProfileById(id);
		List<StudentProfile> studentProfiles = this.getAllStudentProfiles();

		studentProfiles.remove(_studentProfile);

		List<Pair<StudentProfile, Integer>> courseCountList = new LinkedList<>();
		for (StudentProfile studentProfile : studentProfiles) {
			int courseMatchCount = 0;
			List<String> joinedCourses = new ArrayList<>();
			joinedCourses.addAll(studentProfile.getCourseCodes());
			joinedCourses.addAll(_studentProfile.getCourseCodes());

			Set<String> joinedCoursesSet = new HashSet<>(joinedCourses);
			for (String course : joinedCoursesSet) {
				if (Collections.frequency(joinedCourses, course) > 1) {
					courseMatchCount += 1;
				}
			}

			courseCountList.add(Pair.of(studentProfile, courseMatchCount));
		}

		courseCountList.sort(new Comparator<Pair<StudentProfile, Integer>>() {
			@Override
			public int compare(Pair<StudentProfile, Integer> p1, Pair<StudentProfile, Integer> p2) {
				return p1.getRight() - p2.getRight();
			}
		});

		return courseCountList.stream().map(Pair::getLeft).collect(Collectors.toList());
	}

	public double getAbsoluteDistance(Habit habit1, Habit habit2) {
		return Math.sqrt(Math.pow(habit1.getTalkative() - habit2.getTalkative(), 2) +
				Math.pow(habit1.getCollaborative() - habit2.getCollaborative(), 2));
	}

}
