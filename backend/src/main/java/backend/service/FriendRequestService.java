package backend.service;

import backend.form.FriendRequestForm.*;
import backend.model.FriendRequest;

import java.util.List;

public interface FriendRequestService {

	/**
	 * @return All FriendRequests in the FriendRequest Table
	 */
	List<FriendRequest> getAllFriendRequests();

	/**
	 * @param id ID of the FriendRequest to find
	 * @return The designated friend request with the given ID
	 */
	FriendRequest getFriendRequestById(Long id);

	/**
	 * @param fromId ID of the User sending FriendRequest
	 * @return All FriendRequests sent by user with the given id
	 */
	List<FriendRequest> getFriendRequestByFromId(Long fromId);

	/**
	 * @param toId id of the User receiving FriendRequest
	 * @return All FriendRequests received by user with the given id
	 */
	List<FriendRequest> getFriendRequestByToId(Long toId, String status);

	/**
	 * @param input An input defined by CreateFriendRequestForm
	 * @return The created FriendRequest
	 */
	FriendRequest createFriendRequest(CreateFriendRequestForm input);

	/**
	 * @param input An input defined by UpdateFriendRequestForm
	 * @return The updated FriendRequest
	 * @throws backend.exception.exceptions.EntityNotFoundException if the FriendRequest to update could not be found
	 */
	FriendRequest updateFriendRequest(UpdateFriendRequestForm input);

	/**
	 * @param id ID of the FriendRequest to be approved
	 * @return The approved FriendRequest
	 * @throws backend.exception.exceptions.EntityNotFoundException if the FriendRequest could not be found
	 * @throws backend.exception.exceptions.BadRequestException     if the FriendRequest has already been approved/denied
	 */
	FriendRequest approveFriendRequest(Long id);

	/**
	 * @param id ID of the FriendRequest to be denied
	 * @return The denied FriendRequest
	 * @throws backend.exception.exceptions.EntityNotFoundException if the FriendRequest could not be found
	 * @throws backend.exception.exceptions.BadRequestException     if the FriendRequest has already been approved/denied
	 */
	FriendRequest denyFriendRequest(Long id);

	/**
	 * @param id ID of the FriendRequest to be deleted
	 * @return ID of the deleted FriendRequest
	 * @throws backend.exception.exceptions.EntityNotFoundException if the FriendRequest could not be found
	 */
	Long deleteFriendRequest(Long id);

}
