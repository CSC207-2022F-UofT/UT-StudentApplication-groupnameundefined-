package backend.repository;

import backend.model.AptRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AptRequestRepository extends JpaRepository<AptRequest, Long> {

	List<AptRequest> findByFromId(Long fromId);

	@Query("SELECT a FROM AptRequest AS a WHERE a.to.id = :toId AND a.status = :status")
	List<AptRequest> findByToId(@Param("toId") Long toId, @Param("status") String status);

}