package backend.controller.imp;

import backend.model.Habit;
import backend.model.User;
import backend.service.HabitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.controller.HabitController;
import backend.model.Habit;
import backend.repository.HabitRepository;
import backend.service.HabitService;

@CrossOrigin
@RestController
@RequestMapping("/api/habits")
public class HabitControllerImp implements HabitController{

    @Autowired
    HabitService habitService;

    @Override
    @GetMapping("/notification")
    public ResponseEntity<String> getHabitNotification(){
        return new ResponseEntity<>("Habits got.", HttpStatus.OK);
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<List<Habit>> getAllHabits(){
        try {
            List<Habit> habits = habitService.getAllHabits();
            return new ResponseEntity<>(habits, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Habit> getHabitById(Long id){
        Optional<Habit> habit = habitService.getHabitById(id);
        if (habit.isPresent()) {
            return new ResponseEntity<>(habit.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @PostMapping("/create")
    public ResponseEntity<Habit> createHabit(Habit habit){
        try {
            Habit _habit = habitService.createHabit(habit);
            return new ResponseEntity<>(_habit, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/mbti")
    public ResponseEntity<Optional<Habit>>getSameHabitByMBTI(int MBTI){
        try {
            Optional<Habit> habits_MBTI = habitService.getSameHabitByMBTI(MBTI);
            return new ResponseEntity<>(habits_MBTI, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/talkative")
    public ResponseEntity<Optional<Habit>>getSameHabitByTalkative(int talkative){
        try {
            Optional<Habit> habits_Talkative = habitService.getSameHabitByTalkative(talkative);
            return new ResponseEntity<>(habits_Talkative, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/collaborate")
    public ResponseEntity<Optional<Habit>>getSameHabitByCollaborate(int collaborate){
        try {
            Optional<Habit> habits_Collaborate = habitService.getSameHabitByTalkative(collaborate);
            return new ResponseEntity<>(habits_Collaborate, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
