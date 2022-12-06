package backend.controller;

import java.util.List;

import backend.dto.HabitDto;
import backend.form.HabitForm.*;
import org.springframework.http.ResponseEntity;


public interface HabitController {

	ResponseEntity<HabitDto> createHabit(CreateHabitForm input);

	ResponseEntity<List<HabitDto>> getAllHabits();

	ResponseEntity<HabitDto> getHabitById(Long id);

}
