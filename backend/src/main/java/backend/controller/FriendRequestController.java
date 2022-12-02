package backend.controller;

import backend.dto.FriendRequestDto;
import backend.form.FriendRequestForm.*;
import backend.model.FriendRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface FriendRequestController {

	ResponseEntity<FriendRequestDto> createFriendRequest(CreateFriendRequestForm input);

	ResponseEntity<FriendRequestDto> updateFriendRequest(UpdateFriendRequestForm input);

	ResponseEntity<FriendRequestDto> approveFriendRequest(Long id);

	ResponseEntity<FriendRequestDto> denyFriendRequest(Long id);

	ResponseEntity<Long> deleteFriendRequest(Long id);

	ResponseEntity<List<FriendRequestDto>> getAllFriendRequests();

	ResponseEntity<FriendRequestDto> getFriendRequestById(Long id);

	ResponseEntity<List<FriendRequestDto>> getFriendRequestByFromId(Long fromId);

	ResponseEntity<List<FriendRequestDto>> getFriendRequestByToId(Long toId);

}
