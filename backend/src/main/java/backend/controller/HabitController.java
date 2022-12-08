package backend.controller;

import java.util.List;

import backend.dto.HabitDto;
import backend.form.HabitForm.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/habit")
public interface HabitController {

    @PostMapping("/create")
    ResponseEntity<HabitDto> createHabit(@RequestBody CreateHabitForm input);

    @GetMapping("/")
    ResponseEntity<List<HabitDto>> getAllHabits();

    @GetMapping("/{id}")
    ResponseEntity<HabitDto> getHabitById(@PathVariable Long id);

}
