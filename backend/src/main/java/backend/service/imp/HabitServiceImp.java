package backend.service.imp;

<<<<<<< Updated upstream
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.model.Habit;
import backend.repository.HabitRepository;
import backend.service.HabitService;

public class HabitServiceImp implements HabitService{


    @Autowired
    HabitRepository habitRepository;

    @Override
    public List<Habit> getAllHabits(){
        List<Habit> habits = new ArrayList<Habit>();
        habitRepository.findAll().forEach(habits::add);
        System.out.println("Get All Habits");
        return habits;
    }

    @Override
    public Optional<Habit> getHabitById(Long id){
        Optional<Habit> habit = habitRepository.findById(id);
        System.out.println("Get Habit By Id Success");
        return habit;
    }

    @Override
    public Habit createHabit(Habit habit){
        Habit _habit = habitRepository.save(new Habit(habit.getUsername(),habit.getMBTI(), habit.getTalktive(), habit.getCollaborate(), habit.getVisibility()));
        System.out.println("Create Habit Success");
        return _habit;
    }

    @Override
    public Optional<Habit> getSameHabitByMBTI(int MBTI){
        Optional<Habit> habit_MBTI = habitRepository.findByMBTI(MBTI);
        System.out.println("Get Same Habit By MBTI Success");
        return habit_MBTI;
    }

    @Override
    public Optional<Habit> getSameHabitByTalktive(int talktive){
        Optional<Habit> habit_Talktive = habitRepository.findByTalktive(talktive);
        System.out.println("Get Same Habit By Talktive Success");
        return habit_Talktive;
    }

    @Override
    public Optional<Habit> getSameHabitByCollaborate(int collaborate){
        Optional<Habit> habit_Collaborate = habitRepository.findByCollaborate(collaborate);
        System.out.println("Get Same Habit By Collaborate Success");
        return habit_Collaborate;
    }


=======
public class HabitServiceImp implement HabitService {
>>>>>>> Stashed changes
}
