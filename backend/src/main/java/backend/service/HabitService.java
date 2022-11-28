package backend.service;

import java.util.List;
import java.util.Optional;

import backend.model.Habit;


public interface HabitService {


    List<Habit> getAllHabits();

    Optional<Habit> getHabitById(Long id);

    Habit createHabit(Habit habit);

    Optional<Habit> getSameHabitByMBTI(int MBTI);

    Optional<Habit> getSameHabitByTalktive(int talktive);

    Optional<Habit> getSameHabitByCollaborate(int collaborate);





}
