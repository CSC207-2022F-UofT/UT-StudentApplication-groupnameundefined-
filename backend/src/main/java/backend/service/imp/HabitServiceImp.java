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

	@Autowired
	public HabitServiceImp(
			Logger logger,
			HabitRepository habitRepository,
			StudentProfileService studentProfileService
	) {
		this.logger = logger;
		this.habitRepository = habitRepository;
		this.studentProfileService = studentProfileService;
	}

	@Override
	public Habit createHabit(CreateHabitForm input) {
		StudentProfile studentProfile = studentProfileService.getStudentProfileById(input.getStudentProfileId());
		Habit habit = new Habit(input.getTalkative(), input.getCollaborative());

		habit.setStudentProfile(studentProfile);

		return habitRepository.save(habit);
	}

	@Override
	public Habit updateHabit(UpdateHabitForm input) {
		Habit habit = getHabitById(input.getId());

		habit.setTalkative(input.getTalkative());
		habit.setCollaborative(input.getCollaborative());

		return habitRepository.save(habit);
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

}
