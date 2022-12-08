package backend.service;

import java.util.List;
import java.util.Optional;

import backend.form.HabitForm.*;
import backend.model.Habit;


public interface HabitService {

	Habit createHabit(CreateHabitForm input);

	List<Habit> getAllHabits();

	Habit getHabitById(Long id);

	Habit getHabitByStudentProfileId(Long studentProfileid);

}
