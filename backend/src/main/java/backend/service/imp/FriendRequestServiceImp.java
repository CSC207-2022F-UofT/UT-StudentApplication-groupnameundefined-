package backend.service.imp;

import backend.exception.exceptions.BadRequestException;
import backend.exception.exceptions.EntityNotFoundException;
import backend.form.FriendRequestForm.*;
import backend.model.FriendRequest;
import backend.model.User;
import backend.repository.FriendRequestRepository;
import backend.repository.UserRepository;
import backend.service.FriendRequestService;
import backend.service.UserService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FriendRequestServiceImp implements FriendRequestService {

	private final Logger logger;

	private final UserRepository userRepository;
	private final UserService userService;
	private final FriendRequestRepository friendRequestRepository;

	@Autowired
	public FriendRequestServiceImp(
			Logger logger,
			FriendRequestRepository friendRequestRepository,
			UserService userService,
			UserRepository userRepository
	) {
		this.logger = logger;
		this.userRepository = userRepository;
		this.userService = userService;
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

		throw new EntityNotFoundException(
				String.format("Unable to find friend request with id '%d'", id),
				FriendRequest.class
		);
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
			throw new EntityNotFoundException(
					String.format("Unable to find user with id %d", input.getFromId()),
					User.class
			);
		}

		if (_toUser.isEmpty()) {
			throw new EntityNotFoundException(
					String.format("Unable to find user with id %d", input.getToId()),
					User.class
			);
		}

		User fromUser = _fromUser.get();
		User toUser = _toUser.get();

		String message = input.getMessage();

		FriendRequest friendRequest = new FriendRequest(fromUser, toUser, message);

		return friendRequestRepository.save(friendRequest);
	}

	@Override
	public FriendRequest updateFriendRequest(UpdateFriendRequestForm input) {
		Optional<FriendRequest> _friendRequest = friendRequestRepository.findById(input.getId());
		if (_friendRequest.isPresent()) {
			FriendRequest friendRequest = _friendRequest.get();
			friendRequest.setMessage(input.getMessage());

			return friendRequestRepository.save(friendRequest);
		}

		throw new EntityNotFoundException(
				String.format("Unable to find friend request with id %d", input.getId()),
				FriendRequest.class
		);
	}

	@Override
	public FriendRequest approveFriendRequest(Long id) {
		FriendRequest _friendRequest = this.getFriendRequestById(id);

		if (_friendRequest.getStatus().equals("PENDING")) {
			_friendRequest.setStatus("APPROVED");
			FriendRequest friendRequest = friendRequestRepository.save(_friendRequest);
			User fromUser = userService.getUserById(friendRequest.getFrom().getId());
			User toUser = userService.getUserById(friendRequest.getTo().getId());

			fromUser.addFriend(toUser);
			toUser.addFriend(fromUser);
			userRepository.save(fromUser);
			userRepository.save(toUser);

			return friendRequest;
		}
		throw new BadRequestException("The designated friend request has already been processed.");
	}

	@Override
	public FriendRequest denyFriendRequest(Long id) {
		FriendRequest friendRequest = this.getFriendRequestById(id);

		if (friendRequest.getStatus().equals("PENDING")) {
			friendRequest.setStatus("DENIED");
			return friendRequestRepository.save(friendRequest);
		}
		throw new BadRequestException("The designated friend request has already been processed.");
	}

	@Override
	public Long deleteFriendRequest(Long id) {
		friendRequestRepository.deleteById(id);
		return id;
	}
}
