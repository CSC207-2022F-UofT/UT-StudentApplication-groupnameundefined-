package backend.service;

import java.util.List;
import java.util.Optional;

import backend.form.HabitForm.*;
import backend.model.Habit;


public interface HabitService {

	/**
	 * @param input An input defined by CreateHabitForm
	 * @return The created Habit
	 */
	Habit createHabit(CreateHabitForm input);

	/**
	 * Updates a habit
	 *
	 * @param input An input defined by UpdateHabitForm
	 * @return The updated habit
	 */
	Habit updateHabit(UpdateHabitForm input);

	/**
	 * @return All habits from the habit table
	 */
	List<Habit> getAllHabits();

	/**
	 * @param id ID of the habit to find
	 * @return The designated habit with the given id
	 * @throws backend.exception.exceptions.EntityNotFoundException if the habit could not be found
	 */
	Habit getHabitById(Long id);

}
