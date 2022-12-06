package backend.repository;

import backend.model.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TimetableRepository extends JpaRepository<Timetable, Long> {
}
