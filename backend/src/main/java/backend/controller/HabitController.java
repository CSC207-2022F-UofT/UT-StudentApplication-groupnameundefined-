package backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import backend.model.Habit;

public interface HabitController {

    ResponseEntity<String> getHabitNotification();

    ResponseEntity<List<Habit>> getAllHabits();

    ResponseEntity<Habit> getHabitById(Long id);

    ResponseEntity<Habit> createHabit(Habit habit);

    ResponseEntity<List<Habit>>getSameHabitByMBTI(int MBTI);

    ResponseEntity<List<Habit>>getSameHabitByTalktive(int talktive);

    ResponseEntity<List<Habit>>getSameHabitByCollaborate(int collaborate);

    ResponseEntity<String> getHabitSummary(Long id);


}
