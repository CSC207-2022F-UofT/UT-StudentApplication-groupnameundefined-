package backend.controller.imp;

import backend.model.Habit;
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

public class HabitControllerImp {

    @Autowired
    HabitService habitService;

    @GetMapping("/habits/notification")
    ResponseEntity<String> getHabitNotification(){
        return new ResponseEntity<>("Habit got.", HttpStatus.OK);
    }

    @GetMapping("/habits/all")
    ResponseEntity<List<Habit>> getAllHabits(){

    }

    @GetMapping("/habits/{id}/")
    ResponseEntity<String> getHabitSummary(Long id){

    }

    @PostMapping("/habits/create")
    ResponseEntity<Habit> createHabit(Habit habit){

    }

    @GetMapping("/habits/MBTI")
    ResponseEntity<List<Habit>>getSameHabitByMBTI(int MBTI){

    }

    @GetMapping("/habits/Talktive")
    ResponseEntity<List<Habit>>getSameHabitByTalktive(int talktive){

    }

    @GetMapping("/habits/Collaborate")
    ResponseEntity<List<Habit>>getSameHabitByCollaborate(int collaborate){

    }


}
