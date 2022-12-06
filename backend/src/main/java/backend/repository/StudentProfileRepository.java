package backend.repository;

import java.util.List;

import backend.model.Habit;
import backend.model.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {
	@Query("SELECT s FROM StudentProfile s WHERE s.id <> :id ORDER BY SQRT((s.habit.talkative - :t)*(s.habit.talkative - :t) + (s.habit.collaborative - :c)*(s.habit.collaborative - :c)) ASC")
	List<StudentProfile> sortByHabitMatch(
			@Param("id") Long id,
			@Param("t") Integer t,
			@Param("c") Integer c
	);
	
}
