package backend.repository;

import backend.model.AptRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AptRequestRepository extends JpaRepository<AptRequest, Long> {

	List<AptRequest> findByFromId(Long fromId);

	List<AptRequest> findByToId(Long toId);

}