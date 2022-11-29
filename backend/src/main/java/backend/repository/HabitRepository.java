package backend.repository;

import java.util.Optional;

import backend.model.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitRepository extends JpaRepository<Habit, Long> {

    Optional<Habit> findByUserId(Long id);

    Optional<Habit> findByMBTI(int MBTI);

    Optional<Habit> findByTalkative(int talkative);

    Optional<Habit> findByCollaborate(int collaborate);

    boolean existsByMBTI(int MBTI);

    boolean existsByTalkative(int talkative);

    boolean existsByCollaborate(int collaborate);

}
