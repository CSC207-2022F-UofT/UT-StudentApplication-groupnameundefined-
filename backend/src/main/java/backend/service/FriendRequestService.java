package backend.service;

import backend.form.FriendRequestForm.*;
import backend.model.FriendRequest;

import java.util.List;

public interface FriendRequestService {
    List<FriendRequest> getAllFriendRequests();

    FriendRequest getFriendRequestById(Long id);

    List<FriendRequest> getFriendRequestByUserId(Long userId);

    FriendRequest createFriendRequest(CreateFriendRequestForm input);

    FriendRequest approveFriendRequest(Long id);

    FriendRequest denyFriendRequest(Long id);

    Long deleteFriendRequest(Long id);

}
