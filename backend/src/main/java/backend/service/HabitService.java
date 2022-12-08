package backend.service;

import java.util.List;
import java.util.Optional;

import backend.form.HabitForm.*;
import backend.model.Habit;


public interface HabitService {

	Habit createHabit(CreateHabitForm input);

	Habit updateHabit(UpdateHabitForm input);

	List<Habit> getAllHabits();

	Habit getHabitById(Long id);

}
