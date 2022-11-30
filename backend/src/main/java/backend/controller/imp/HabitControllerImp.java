package backend.controller.imp;

import backend.dto.HabitDto;
import backend.form.HabitForm.*;
import backend.mappers.HabitMapper;
import backend.model.Habit;
import backend.service.HabitService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.controller.HabitController;

@CrossOrigin
@RestController
@RequestMapping("/api/habits")
public class HabitControllerImp implements HabitController {

    private final Logger logger;

    private final HabitService habitService;
    private final HabitMapper habitMapper;

    @Autowired
    public HabitControllerImp(Logger logger, HabitService habitService, HabitMapper habitMapper) {
        this.logger = logger;
        this.habitService = habitService;
        this.habitMapper = habitMapper;
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<HabitDto> createHabit(CreateHabitForm input) {
        Habit habit = habitService.createHabit(input);
        HabitDto habitDto = habitMapper.toDto(habit);

        return new ResponseEntity<>(habitDto, HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<List<HabitDto>> getAllHabits() {
        List<Habit> habits = habitService.getAllHabits();
        List<HabitDto> habitDtos = habitMapper.toDtoList(habits);

        return new ResponseEntity<>(habitDtos, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<HabitDto> getHabitById(@PathVariable Long id) {
        Habit habit = habitService.getHabitById(id);
        HabitDto habitDto = habitMapper.toDto(habit);

        return new ResponseEntity<>(habitDto, HttpStatus.OK);
    }

    @Override
    @GetMapping("/filter")
    public ResponseEntity<List<HabitDto>> getFilteredHabits(
            @RequestParam(required = false) Integer mbti,
            @RequestParam(required = false) Integer talkative,
            @RequestParam(required = false) Integer collaborative
    ) {
        try {
            List<Habit> habits = habitService.getFilteredHabits(mbti, talkative, collaborative);
            List<HabitDto> habitDtos = habitMapper.toDtoList(habits);

            return new ResponseEntity<>(habitDtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
