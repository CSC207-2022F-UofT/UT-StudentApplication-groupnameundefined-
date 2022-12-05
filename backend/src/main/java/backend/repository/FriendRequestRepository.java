package backend.repository;

import backend.model.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {

	List<FriendRequest> findByFromId(Long fromId);

	List<FriendRequest> findByToId(Long toId);

}
