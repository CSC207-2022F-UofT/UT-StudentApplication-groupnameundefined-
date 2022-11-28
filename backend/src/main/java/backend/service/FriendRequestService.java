package backend.service;

import backend.model.FriendRequest;

import java.util.List;

public interface FriendRequestService {
    List<FriendRequest> getAllFriendRequests();

    FriendRequest getFriendRequestById(Long id);

    List<FriendRequest> getFriendRequestByUserId(Long userId);

    FriendRequest createFriendRequest(FriendRequest friendRequest);

    FriendRequest approveFriendRequest(Long id);

    FriendRequest denyFriendRequest(Long id);

    Long deleteFriendRequest(Long id);

    FriendRequest updateFriendRequest(FriendRequest friendRequest);
}
