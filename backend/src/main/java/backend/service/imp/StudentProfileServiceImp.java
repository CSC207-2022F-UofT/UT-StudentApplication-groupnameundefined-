package backend.service.imp;

import java.util.*;
import java.util.stream.Collectors;

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

@Service
public class StudentProfileServiceImp implements StudentProfileService {

	private final Logger logger;
	private final StudentProfileRepository studentProfileRepository;
	private final UserRepository userRepository;
	private final SectionBlockRepository sectionBlockRepository;
	private final TimetableService timetableService;

	private final HabitRepository habitRepository;

	private final TimetableRepository timetableRepository;

	@Autowired
	public StudentProfileServiceImp(
			Logger logger,
			StudentProfileRepository studentProfileRepository,
			UserRepository userRepository,
			SectionBlockRepository sectionBlockRepository,
			TimetableService timetableService,
			HabitRepository habitRepository,
			TimetableRepository timetableRepository
	) {
		this.logger = logger;
		this.studentProfileRepository = studentProfileRepository;
		this.userRepository = userRepository;
		this.sectionBlockRepository = sectionBlockRepository;
		this.timetableService = timetableService;
		this.habitRepository = habitRepository;
		this.timetableRepository = timetableRepository;
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
		StudentProfile studentProfile = new StudentProfile(
				input.getProgram(),
				input.getCollege(),
				input.getEnrolmentYear()
		);
		Habit habit = new Habit();
		user.setStudentProfile(studentProfile);
		studentProfile.setUser(user);

		Timetable timetable = new Timetable();
		timetableRepository.save(timetable);
		timetable.setStudentProfile(studentProfile);
		studentProfile.setTimetable(timetable);

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

		throw new EntityNotFoundException(
				String.format("Unable to find student profile with id '%d'.", id),
				StudentProfile.class
		);
	}

	@Override
	public List<StudentProfile> matchStudentProfiles(MatchStudentProfileForm input) {
		StudentProfile studentProfile = getStudentProfileById(input.getStudentProfileId());

		List<String> matchAttrs = input.getMatchBy();
		if (matchAttrs.size() == 1) {
			if (matchAttrs.get(0).equals("HABIT")) {
				return matchStudentProfileByHabit(studentProfile);
			} else {
				return matchStudentProfileByCourses(studentProfile);
			}
		} else {
			List<StudentProfile> matchByHabitResults = matchStudentProfileByHabit(studentProfile);
			return sortStudentProfileByCourses(matchByHabitResults, studentProfile)
					.subList(0, Math.min(20, matchByHabitResults.size()));
		}
	}


	@Override
	public List<StudentProfile> matchStudentProfileByHabit(StudentProfile studentProfile) {
		Habit habit = studentProfile.getHabit();

		return studentProfileRepository.sortByHabitMatch(
				studentProfile.getId(),
				habit.getTalkative(),
				habit.getCollaborative()
		);
	}

	@Override
	public List<StudentProfile> matchStudentProfileByCourses(StudentProfile studentProfile) {
		List<StudentProfile> studentProfiles = this.getAllStudentProfiles();

		return sortStudentProfileByCourses(studentProfiles, studentProfile);
	}

	@Override
	public List<StudentProfile> sortStudentProfileByCourses(
			List<StudentProfile> studentProfiles,
			StudentProfile studentProfile
	) {
		studentProfiles.remove(studentProfile);

		List<Pair<StudentProfile, Integer>> courseCountList = new LinkedList<>();
		for (StudentProfile targetStudentProfile : studentProfiles) {
			List<String> joinedCourses = new ArrayList<>();
			joinedCourses.addAll(targetStudentProfile.getCourseCodes());
			joinedCourses.addAll(studentProfile.getCourseCodes());

			Set<String> joinedCoursesSet = new HashSet<>(joinedCourses);
			courseCountList.add(Pair.of(studentProfile, joinedCourses.size() - joinedCoursesSet.size()));
		}

		courseCountList.sort((p1, p2) -> {
			if (getAbsoluteDistance(p1.getLeft().getHabit(), p2.getLeft().getHabit()) == 0) {
				return p1.getRight() - p2.getRight();
			}
			return 0;
		});

		return courseCountList.stream().map(Pair::getLeft).collect(Collectors.toList());
	}

	public double getAbsoluteDistance(Habit habit1, Habit habit2) {
		return Math.sqrt(Math.pow(habit1.getTalkative() - habit2.getTalkative(), 2) +
				Math.pow(habit1.getCollaborative() - habit2.getCollaborative(), 2));
	}

}
