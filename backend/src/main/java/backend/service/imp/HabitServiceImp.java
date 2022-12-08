package backend.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import backend.exception.exceptions.EntityNotFoundException;
import backend.form.HabitForm.*;
import backend.model.StudentProfile;
import backend.repository.StudentProfileRepository;
import backend.service.StudentProfileService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import backend.model.Habit;
import backend.repository.HabitRepository;
import backend.service.HabitService;
import org.springframework.stereotype.Service;

@Service
public class HabitServiceImp implements HabitService {

	private final Logger logger;

	private final HabitRepository habitRepository;
	private final StudentProfileService studentProfileService;
	private final StudentProfileRepository studentProfileRepository;

	@Autowired
	public HabitServiceImp(
			Logger logger,
			HabitRepository habitRepository,
			StudentProfileService studentProfileService,
			StudentProfileRepository studentProfileRepository
	) {
		this.logger = logger;
		this.habitRepository = habitRepository;
		this.studentProfileService = studentProfileService;
		this.studentProfileRepository = studentProfileRepository;
	}

	@Override
	public Habit createHabit(CreateHabitForm input) {
		StudentProfile _studentProfile = studentProfileService.getStudentProfileById(input.getStudentProfileId());
		Habit habit = new Habit(input.getTalkative(), input.getCollaborative());

		_studentProfile.setHabit(habit);
		habit.setStudentProfile(_studentProfile);

		StudentProfile studentProfile = studentProfileRepository.save(_studentProfile);

		return studentProfile.getHabit();
	}


	@Override
	public List<Habit> getAllHabits() {
		return new ArrayList<Habit>(habitRepository.findAll());
	}

	@Override
	public Habit getHabitById(Long id) {
		Optional<Habit> habit = habitRepository.findById(id);
		if (habit.isPresent()) {
			return habit.get();
		}

		throw new EntityNotFoundException(String.format("Unable to find course with id '%d'", id), Habit.class);
	}

	@Override
	public Habit getHabitByStudentProfileId(Long studentProfileId) {
		Optional<Habit> habit = habitRepository.findByStudentProfileId(studentProfileId);
		if (habit.isPresent()) {
			return habit.get();
		}

		throw new EntityNotFoundException(String.format("Unable to find course with student Profile id '%d'", studentProfileId), Habit.class);
	}

}
