package backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import backend.model.Habit;

public interface HabitController {

    ResponseEntity<String> getHabitNotification();

    ResponseEntity<List<Habit>> getAllHabits();

    ResponseEntity<Habit> getHabitById(Long id);

    ResponseEntity<Habit> createHabit(Habit habit);

    ResponseEntity<Optional<Habit>>getSameHabitByMBTI(int MBTI);

    ResponseEntity<Optional<Habit>>getSameHabitByTalkative(int talkative);

    ResponseEntity<Optional<Habit>>getSameHabitByCollaborate(int collaborate);


}
