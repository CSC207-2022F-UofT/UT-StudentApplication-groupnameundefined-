package backend.repository;

import java.util.Optional;

import backend.model.Habit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HabitRepository extends JpaRepository<Habit, Long> {

	@Query("SELECT h FROM Habit AS h WHERE h.studentProfile.user.id = :id")
	Optional<Habit> findByUserId(@Param("id") Long id);

	Optional<Habit> findByStudentProfileId(Long id);

}
