package backend.service;

import backend.form.FriendRequestForm.*;
import backend.model.FriendRequest;

import java.util.List;

public interface FriendRequestService {

    /**
     * @return all FriendRequests in the FriendRequest Table
     */
    List<FriendRequest> getAllFriendRequests();

    /**
     * @param id of a FriendRequest
     * @return the FriendRequest this id refers to
     */
    FriendRequest getFriendRequestById(Long id);

    /**
     * @param fromId id of the User sending FriendRequest
     * @return all FriendRequests sent by user with the given id
     */
    List<FriendRequest> getFriendRequestByFromId(Long fromId);

    /**
     * @param toId id of the User receiving FriendRequest
     * @return all FriendRequests received by user with the given id
     */
    List<FriendRequest> getFriendRequestByToId(Long toId);

    /**
     * @param input an input of the form defined in CreateFriendRequestForm
     * @return a FriendRequest created with given input
     */
    FriendRequest createFriendRequest(CreateFriendRequestForm input);

    /**
     * @param input an input of the form defined in UpdateFriendRequestForm
     * @return the updated FriendRequest with given input
     */
    FriendRequest updateFriendRequest(UpdateFriendRequestForm input);

    /**
     * @param id id of the FriendRequest needs to be approved
     * @return the approved FriendRequest
     */
    FriendRequest approveFriendRequest(Long id);

    /**
     * @param id id of the FriendRequest needs to be denied
     * @return the denied FriendRequest
     */
    FriendRequest denyFriendRequest(Long id);

    /**
     * @param id id of the FriendRequest needs to be deleted
     * @return id of the deleted FriendRequest
     */
    Long deleteFriendRequest(Long id);

}
