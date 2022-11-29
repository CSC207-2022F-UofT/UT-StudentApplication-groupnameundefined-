package backend.controller;

import java.util.List;
import java.util.Optional;

import backend.dto.HabitDto;
import org.springframework.http.ResponseEntity;

import backend.model.Habit;

public interface HabitController {

    ResponseEntity<HabitDto> createHabit(CreateHabitForm input);

    ResponseEntity<List<HabitDto>> getAllHabits();

    ResponseEntity<HabitDto> getHabitById(Long id);
    
    ResponseEntity<List<HabitDto>> getFilteredHabits(Integer mbti, Integer talktative, Integer collaborative);

}
