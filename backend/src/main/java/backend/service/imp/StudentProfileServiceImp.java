package backend.service.imp;

import java.io.File;
import java.util.*;

import backend.exception.exceptions.EntityNotFoundException;
import backend.model.*;
import backend.repository.SectionBlockRepository;
import backend.service.TimetableService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.form.StudentProfileForm.CreateStudentProfileForm;
import backend.repository.StudentProfileRepository;
import backend.repository.UserRepository;
import backend.repository.HabitRepository;
import backend.service.StudentProfileService;
import backend.service.HabitService;
import org.springframework.web.multipart.MultipartFile;;

@Service
public class StudentProfileServiceImp implements StudentProfileService {

	private final Logger logger;
	private final StudentProfileRepository studentProfileRepository;
	private final UserRepository userRepository;
	private final SectionBlockRepository sectionBlockRepository;
	private final TimetableService timetableService;

	@Autowired
	public StudentProfileServiceImp(
			Logger logger,
			StudentProfileRepository studentProfileRepository,
			UserRepository userRepository,
			SectionBlockRepository sectionBlockRepository,
			TimetableService timetableService
	) {
		this.logger = logger;
		this.studentProfileRepository = studentProfileRepository;
		this.userRepository = userRepository;
		this.sectionBlockRepository = sectionBlockRepository;
		this.timetableService = timetableService;
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

	/*
	First find those with at least exactly equal parameters
	If null, find those with at least one |diff| = 1 parameters
	If null, find those with at least one |diff| = 2 parameters
	If null, randomly return 10 users.*/
	@Override
	public List<StudentProfile> matchStudentProfileByProperty(Long id, String property){

		List<StudentProfile> result = new ArrayList<StudentProfile>;
		Habit my_habit = habitRepository.findByStudentProfileId(id);
		HabitVisibility my_habit_visibility = my_habit.getVisibility();


		Hashtable<String, Integer> my_search = new Hashtable<String, Integer>();

		if(property == 'MBTI' && my_habit_visibility.getMbti()){
			my_search.put("MBTI", my_habit.getMbti());
		}else if(property == 'talkative' && my_habit_visibility.getTalkative()){
			my_search.put("talkative", my_habit.getTalkative());
		}else if(property == 'collaborate' && my_habit_visibility.getCollaborate()){
			my_search.put("collaborate", my_habit.getCollaborate());
		}else{ throw new EntityNotFoundException("Invalid property name");}

		int diff = 0;
		List<Habit> result_habit = new ArrayList<Habit>;
		List<Long>  result_habit_sp_id = new ArrayList<Long>;

		while(!result.size() && diff <= 2) {
			result_habit = HabitService.getFilteredHabits(my_search, diff);
			result_habit_sp_id.clean();

			if (!result_habit.size()){
				for(Habit i: result_habit){
					if(i.getStudentProfileId() != null){
						result_habit_sp_id.add(i.getStudentProfileId());}
				}
				result.addAll(studentProfileRepository.findByIdIn(result_habit_sp_id));
			}
			diff += 1;
		}

		if(!result.size()){
			result_habit = HabitRespository.findRandHabit();
			for(Habit i: result_habit){
				if(i.getStudentProfileId() != null){
					result_habit_sp_id.add(i.getStudentProfileId());}
			}
			result.addAll(studentProfileRepository.findByIdIn(result_habit_sp_id));
		}
		return result;
	}
}
