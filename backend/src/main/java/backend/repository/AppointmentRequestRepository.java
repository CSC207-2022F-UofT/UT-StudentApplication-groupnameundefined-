package backend.repository;

import backend.model.AppointmentRequest;
import backend.model.FriendRequest;
import backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppointmentRequestRepository extends JpaRepository<AppointmentRequest, Long> {

	List<AppointmentRequest> findByFromId(Long fromId);

}