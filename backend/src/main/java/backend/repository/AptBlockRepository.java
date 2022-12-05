package backend.repository;

import backend.model.AptBlock;
import backend.model.AptRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AptBlockRepository extends JpaRepository<AptBlock, Long> {

	List<AptBlock> findByTimetablesId(Long timetableId);

}