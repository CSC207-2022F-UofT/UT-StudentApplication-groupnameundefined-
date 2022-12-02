package backend.service.imp;

import backend.exception.exceptions.EntityNotFoundException;
import backend.form.FriendRequestForm.*;
import backend.model.FriendRequest;
import backend.model.Section;
import backend.model.User;
import backend.repository.FriendRequestRepository;
import backend.repository.UserRepository;
import backend.service.FriendRequestService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FriendRequestServiceImp implements FriendRequestService {

	private final Logger logger;

	private final UserRepository userRepository;
	private final FriendRequestRepository friendRequestRepository;

	@Autowired
	public FriendRequestServiceImp(
			Logger logger,
			FriendRequestRepository friendRequestRepository,
			UserRepository userRepository
	) {
		this.logger = logger;
		this.userRepository = userRepository;
		this.friendRequestRepository = friendRequestRepository;
	}


	@Override
	public List<FriendRequest> getAllFriendRequests() {
		return friendRequestRepository.findAll();
	}

	@Override
	public FriendRequest getFriendRequestById(Long id) {
		Optional<FriendRequest> friendRequest = friendRequestRepository.findById(id);
		if (friendRequest.isPresent()) {
			return friendRequest.get();
		}

		throw new EntityNotFoundException(String.format("Unable to find friend request with id '%d'", id), FriendRequest.class);
	}

	@Override
	public List<FriendRequest> getFriendRequestByFromId(Long fromId) {
		return friendRequestRepository.findByFromId(fromId);
	}

	@Override
	public List<FriendRequest> getFriendRequestByToId(Long toId) {
		return friendRequestRepository.findByToId(toId);
	}

	@Override
	public FriendRequest createFriendRequest(CreateFriendRequestForm input) {
		Optional<User> _fromUser = userRepository.findById(input.getFromId());
		Optional<User> _toUser = userRepository.findById(input.getToId());

		if (_fromUser.isEmpty()) {
			throw new EntityNotFoundException(String.format("Unable to find user with id %d", input.getFromId()), User.class);
		}

		if (_toUser.isEmpty()) {
			throw new EntityNotFoundException(String.format("Unable to find user with id %d", input.getToId()), User.class);
		}

		User fromUser = _fromUser.get();
		User toUser = _toUser.get();

		String message = input.getMessage();

		FriendRequest _friendRequest = new FriendRequest(message);
		FriendRequest friendRequest = friendRequestRepository.save(_friendRequest);

		fromUser.addSentFriendRequest(friendRequest);
		toUser.addReceivedFriendRequest(friendRequest);

		userRepository.save(fromUser);
		userRepository.save(toUser);

		return friendRequest;
	}

	@Override
	public FriendRequest updateFriendRequest(UpdateFriendRequestForm input) {
		Optional<FriendRequest> _friendRequest = friendRequestRepository.findById(input.getId());
		if (_friendRequest.isPresent()) {
			FriendRequest friendRequest = _friendRequest.get();
			friendRequest.setMessage(input.getMessage());

			return friendRequestRepository.save(friendRequest);
		}

		throw new EntityNotFoundException(String.format("Unable to find friend request with id %d", input.getId()), FriendRequest.class);
	}

	@Override
	public FriendRequest approveFriendRequest(Long id) {
		Optional<FriendRequest> _friendRequest = friendRequestRepository.findById(id);
		if (_friendRequest.isPresent()) {
			FriendRequest friendRequest = _friendRequest.get();
			friendRequest.setStatus("APPROVED");

			return friendRequestRepository.save(friendRequest);
		}

		throw new EntityNotFoundException(String.format("Unable to find friend request with id %d", id), FriendRequest.class);
	}

	@Override
	public FriendRequest denyFriendRequest(Long id) {
		Optional<FriendRequest> _friendRequest = friendRequestRepository.findById(id);
		if (_friendRequest.isPresent()) {
			FriendRequest friendRequest = _friendRequest.get();
			friendRequest.setStatus("DENIED");

			return friendRequestRepository.save(friendRequest);
		}

		throw new EntityNotFoundException(String.format("Unable to find friend request with id %d", id), FriendRequest.class);
	}

	@Override
	public Long deleteFriendRequest(Long id) {
		friendRequestRepository.deleteById(id);
		return id;
	}
}
