package backend.controller;

import java.util.List;

import backend.dto.HabitDto;
import backend.form.HabitForm.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/habit")
public interface HabitController {


	@PostMapping("/create")
	ResponseEntity<HabitDto> createHabit(CreateHabitForm input);

	@GetMapping("/view/all")
	ResponseEntity<List<HabitDto>> getAllHabits();

	@GetMapping("/view/{id}")
	ResponseEntity<HabitDto> getHabitById(Long id);

	@GetMapping("/view/profile/{id}")
	ResponseEntity<HabitDto> getHabitByStudentProfileId(Long studentProfileId);

}
