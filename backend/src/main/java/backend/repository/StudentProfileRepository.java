package backend.repository;

import java.util.List;

import backend.model.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {
}
