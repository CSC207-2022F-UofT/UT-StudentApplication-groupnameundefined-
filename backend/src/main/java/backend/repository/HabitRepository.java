package backend.repository;

import java.util.Optional;

import backend.model.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitRepository extends JpaRepository<Habit, Long> {
    Optional<Habit> findById(Long Id);

    Optional<Habit> findByUsername(String username);

    Optional<Habit> findByMBTI(int MBTI);

    Optional<Habit> findByTalkative(int talkative);

    Optional<Habit> findByCollaborate(int collaborate);

    boolean existsById(Long Id);

    boolean existsByUsername(String username);

    boolean existsByMBTI(int MBTI);

    boolean existsByTalkative(int talkative);

    boolean existsByCollaborate(int collaborate);












}
