package backend.service.imp;

import java.util.*;
import java.util.stream.Collectors;

import backend.exception.exceptions.BadRequestException;
import backend.repository.*;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.exception.exceptions.EntityNotFoundException;
import backend.form.StudentProfileForm.*;

import backend.service.StudentProfileService;
import backend.model.*;
import backend.service.TimetableService;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StudentProfileServiceImp implements StudentProfileService {

	private final Logger logger;

	private final StudentProfileRepository studentProfileRepository;

	private final UserRepository userRepository;

	private final TimetableRepository timetableRepository;

	private final TimetableService timetableService;

	private final SectionBlockRepository sectionBlockRepository;

	@Autowired
	public StudentProfileServiceImp(
			Logger logger,
			StudentProfileRepository studentProfileRepository,
			UserRepository userRepository,
			TimetableRepository timetableRepository,
			TimetableService timetableService,
			SectionBlockRepository sectionBlockRepository
	) {
		this.logger = logger;
		this.studentProfileRepository = studentProfileRepository;
		this.userRepository = userRepository;
		this.timetableRepository = timetableRepository;
		this.timetableService = timetableService;
		this.sectionBlockRepository = sectionBlockRepository;
	}

	@Override
	public StudentProfile createStudentProfile(CreateStudentProfileForm input) {
		Optional<User> _user = userRepository.findById(input.getUserId());
		if (_user.isEmpty()) {
			throw new EntityNotFoundException(
					String.format("Unable to find user with id %d", input.getUserId()),
					User.class
			);
		}

		User user = _user.get();

		if (user.getStudentProfile() != null) {
			throw new BadRequestException("Student profile for user " + user.getName() + " already exists.");
		}
		
		StudentProfile studentProfile = new StudentProfile(
				input.getProgram(),
				input.getCollege(),
				input.getEnrolmentYear()
		);

		user.setStudentProfile(studentProfile);
		studentProfile.setUser(user);

		Timetable timetable = new Timetable();
		timetableRepository.save(timetable);
		timetable.setStudentProfile(studentProfile);

		Habit habit = new Habit();

		studentProfile.setTimetable(timetable);

		return userRepository.save(user).getStudentProfile();
	}

	@Override
	public StudentProfile loadCourseIcs(Long studentProfileId, String session, MultipartFile iCalendar) {
		StudentProfile studentProfile = getStudentProfileById(studentProfileId);

		List<Map<String, String>> sectionData = timetableService.parseIcs(iCalendar);

		for (Map<String, String> sec : sectionData) {
			List<SectionBlock> sectionBlocks = sectionBlockRepository.findByCode(session, sec.get("course"), sec.get("section"));
			for (SectionBlock sectionBlock : sectionBlocks) {
				sectionBlock.addTimetable(studentProfile.getTimetable());
				studentProfile.addCourse(sectionBlock.getSection().getCourse().getCode());
			}
		}

		return studentProfileRepository.save(studentProfile);
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

		throw new EntityNotFoundException(
				String.format("Unable to find student profile with id '%d'.", id),
				StudentProfile.class
		);
	}

	@Override
	public List<StudentProfile> matchStudentProfiles(Long id, String criteria) {
		StudentProfile studentProfile = getStudentProfileById(id);
		List<StudentProfile> results = new ArrayList<>();

		if (criteria.equals("HABIT")) {
			results.addAll(matchStudentProfilesByHabit(studentProfile));
		} else if (criteria.equals("COURSE")) {
			results.addAll(matchStudentProfilesByCourses(studentProfile));
		} else {
			List<StudentProfile> matchByHabitResults = matchStudentProfilesByHabit(studentProfile);
			results.addAll(sortStudentProfilesByCourses(matchByHabitResults, studentProfile, true));
		}

		return results.subList(0, Math.min(20, results.size()));
	}


	@Override
	public List<StudentProfile> matchStudentProfilesByHabit(StudentProfile studentProfile) {
		Habit habit = studentProfile.getHabit();

		return studentProfileRepository.sortByHabitMatch(
				studentProfile.getId(),
				habit.getTalkative(),
				habit.getCollaborative()
		);
	}

	@Override
	public List<StudentProfile> matchStudentProfilesByCourses(StudentProfile studentProfile) {
		List<StudentProfile> studentProfiles = this.getAllStudentProfiles();

		return sortStudentProfilesByCourses(studentProfiles, studentProfile, false);
	}

	@Override
	public List<StudentProfile> sortStudentProfilesByCourses(
			List<StudentProfile> studentProfiles,
			StudentProfile targetStudentProfile,
			Boolean isSecondarySort
	) {
		List<Pair<StudentProfile, Integer>> courseCountList = new LinkedList<>();
		for (StudentProfile studentProfile : studentProfiles) {
			if (studentProfile.getId().equals(targetStudentProfile.getId())) {
				continue;
			}
			List<String> joinedCourses = new ArrayList<>();
			joinedCourses.addAll(studentProfile.getCourseCodes());
			joinedCourses.addAll(targetStudentProfile.getCourseCodes());

			Set<String> joinedCoursesSet = new HashSet<>(joinedCourses);
			courseCountList.add(Pair.of(studentProfile, joinedCourses.size() - joinedCoursesSet.size()));
		}

		courseCountList.sort((p1, p2) -> {
			if (isSecondarySort) {
				if (getAbsoluteDistance(p1.getLeft().getHabit(), p2.getLeft().getHabit()) == 0) {
					return p2.getRight() - p1.getRight();
				} else {
					return 0;
				}
			}
			return p2.getRight() - p1.getRight();
		});

		return courseCountList.stream().map(Pair::getLeft).toList();
	}

	public double getAbsoluteDistance(Habit habit1, Habit habit2) {
		return Math.sqrt(Math.pow(habit1.getTalkative() - habit2.getTalkative(), 2) +
				Math.pow(habit1.getCollaborative() - habit2.getCollaborative(), 2));
	}

}
