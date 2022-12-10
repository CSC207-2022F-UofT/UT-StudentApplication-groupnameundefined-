package backend.controller.imp;

import backend.controller.FriendRequestController;
import backend.dto.FriendRequestDto;
import backend.form.FriendRequestForm.*;
import backend.mappers.FriendRequestMapper;
import backend.model.FriendRequest;
import backend.service.FriendRequestService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class FriendRequestControllerImp implements FriendRequestController {

	private final Logger logger;

	private final FriendRequestService friendRequestService;

	private final FriendRequestMapper friendRequestMapper;

	@Autowired
	public FriendRequestControllerImp(
			Logger logger,
			FriendRequestService friendRequestService,
			FriendRequestMapper friendRequestMapper
	) {
		this.logger = logger;
		this.friendRequestService = friendRequestService;
		this.friendRequestMapper = friendRequestMapper;
	}

	@Override
	public ResponseEntity<List<FriendRequestDto>> getAllFriendRequests() {
		List<FriendRequest> friendRequests = friendRequestService.getAllFriendRequests();
		List<FriendRequestDto> friendRequestDtos = friendRequestMapper.toDtoList(friendRequests);

		return new ResponseEntity<>(friendRequestDtos, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<FriendRequestDto> getFriendRequestById(Long id) {
		FriendRequest friendRequest = friendRequestService.getFriendRequestById(id);
		FriendRequestDto friendRequestDto = friendRequestMapper.toDto(friendRequest);

		return new ResponseEntity<>(friendRequestDto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<FriendRequestDto>> getFriendRequestByFromId(Long fromId) {
		List<FriendRequest> friendRequests = friendRequestService.getFriendRequestByFromId(fromId);
		List<FriendRequestDto> friendRequestDtos = friendRequestMapper.toDtoList(friendRequests);

		return new ResponseEntity<>(friendRequestDtos, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<FriendRequestDto>> getFriendRequestByToId(Long toId, String status) {
		List<FriendRequest> friendRequests = friendRequestService.getFriendRequestByToId(toId, status);
		List<FriendRequestDto> friendRequestDtos = friendRequestMapper.toDtoList(friendRequests);
		logger.info(friendRequestDtos.stream().map(FriendRequestDto::getId).toList().toString());

		return new ResponseEntity<>(friendRequestDtos, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<FriendRequestDto> createFriendRequest(CreateFriendRequestForm input) {
		FriendRequest friendRequest = friendRequestService.createFriendRequest(input);
		FriendRequestDto friendRequestDto = friendRequestMapper.toDto(friendRequest);

		return new ResponseEntity<>(friendRequestDto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<FriendRequestDto> approveFriendRequest(Long id) {
		FriendRequest friendRequest = friendRequestService.approveFriendRequest(id);
		FriendRequestDto friendRequestDto = friendRequestMapper.toDto(friendRequest);

		return new ResponseEntity<>(friendRequestDto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<FriendRequestDto> denyFriendRequest(Long id) {
		FriendRequest friendRequest = friendRequestService.denyFriendRequest(id);
		FriendRequestDto friendRequestDto = friendRequestMapper.toDto(friendRequest);

		return new ResponseEntity<>(friendRequestDto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Long> deleteFriendRequest(Long id) {
		Long deletedId = friendRequestService.deleteFriendRequest(id);

		return new ResponseEntity<>(deletedId, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<FriendRequestDto> updateFriendRequest(UpdateFriendRequestForm input) {
		FriendRequest friendRequest = friendRequestService.updateFriendRequest(input);
		FriendRequestDto friendRequestDto = friendRequestMapper.toDto(friendRequest);

		return new ResponseEntity<>(friendRequestDto, HttpStatus.OK);
	}

}
