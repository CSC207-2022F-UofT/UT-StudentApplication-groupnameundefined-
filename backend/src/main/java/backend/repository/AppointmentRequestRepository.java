package backend.repository;

import backend.model.AppointmentRequest;
import backend.model.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppointmentRequestRepository extends JpaRepository<AppointmentRequest, Long> {

    @Query("SELECT r FROM Request AS r WHERE r.from = :userId")
    List<AppointmentRequest> findByUserId(@Param("userId") Long userId);
}
