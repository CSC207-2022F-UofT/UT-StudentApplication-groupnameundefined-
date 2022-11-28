package backend.controller;

import backend.model.FriendRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface FriendRequestController {

    ResponseEntity<List<FriendRequest>> getAllFriendRequests();

    ResponseEntity<FriendRequest> getFriendRequestById(Long id);

    ResponseEntity<List<FriendRequest>> getFriendRequestByUserId(Long userId);

    ResponseEntity<FriendRequest> createFriendRequest(FriendRequest friendRequest);

    ResponseEntity<FriendRequest> approveFriendRequest(Long id);

    ResponseEntity<FriendRequest> denyFriendRequest(Long id);

    ResponseEntity<Long> deleteFriendRequest(Long id);

    ResponseEntity<FriendRequest> updateFriendRequest(FriendRequest friendRequest);


}
