package backend.service;

import backend.form.FriendRequestForm.*;
import backend.model.FriendRequest;

import java.util.List;

public interface FriendRequestService {
    List<FriendRequest> getAllFriendRequests();

    FriendRequest getFriendRequestById(Long id);

    List<FriendRequest> getFriendRequestByFromId(Long fromId);

    List<FriendRequest> getFriendRequestByToId(Long toId);

    FriendRequest createFriendRequest(CreateFriendRequestForm input);

    FriendRequest updateFriendRequest(UpdateFriendRequestForm input);

    FriendRequest approveFriendRequest(Long id);

    FriendRequest denyFriendRequest(Long id);

    Long deleteFriendRequest(Long id);

}
