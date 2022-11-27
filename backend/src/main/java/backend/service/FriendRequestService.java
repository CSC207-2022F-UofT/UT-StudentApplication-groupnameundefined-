package backend.service;

import backend.model.FriendRequest;
import backend.model.Request;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface FriendRequestService {
    List<FriendRequest> getAllFriendRequests();

    Optional<FriendRequest> getFriendRequestById(Long id);

    FriendRequest createFriendRequest(FriendRequest friendRequest);

    FriendRequest approveFriendRequest(Long id);

    FriendRequest denyFriendRequest(Long id);

    Long deleteFriendRequest(Long id);

    FriendRequest updateFriendRequest(FriendRequest friendRequest);
}
