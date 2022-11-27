package backend.controller;

import backend.model.FriendRequest;
import backend.model.Request;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface FriendRequestController {

    ResponseEntity<List<FriendRequest>> getAllFriendRequests();

    ResponseEntity<FriendRequest> getFriendRequestById(Long id);

    ResponseEntity<FriendRequest> createFriendRequest(FriendRequest friendRequest);

    ResponseEntity<FriendRequest> approveFriendRequest(Long id);

    ResponseEntity<FriendRequest> denyFriendRequest(Long id);

    ResponseEntity<Long> deleteFriendRequest(Long id);

    ResponseEntity<FriendRequest> updateFriendRequest(FriendRequest friendRequest);


}
