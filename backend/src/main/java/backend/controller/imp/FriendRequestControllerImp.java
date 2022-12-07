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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/friend-request")
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
	@GetMapping("/")
	public ResponseEntity<List<FriendRequestDto>> getAllFriendRequests() {
		List<FriendRequest> friendRequests = friendRequestService.getAllFriendRequests();
		List<FriendRequestDto> friendRequestDtos = friendRequestMapper.toDtoList(friendRequests);

		return new ResponseEntity<>(friendRequestDtos, HttpStatus.OK);
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<FriendRequestDto> getFriendRequestById(@PathVariable Long id) {
		FriendRequest friendRequest = friendRequestService.getFriendRequestById(id);
		FriendRequestDto friendRequestDto = friendRequestMapper.toDto(friendRequest);

		return new ResponseEntity<>(friendRequestDto, HttpStatus.OK);
	}

	@Override
	@GetMapping("/from/{fromId}")
	public ResponseEntity<List<FriendRequestDto>> getFriendRequestByFromId(@PathVariable Long fromId) {
		List<FriendRequest> friendRequests = friendRequestService.getFriendRequestByFromId(fromId);
		List<FriendRequestDto> friendRequestDtos = friendRequestMapper.toDtoList(friendRequests);

		return new ResponseEntity<>(friendRequestDtos, HttpStatus.OK);
	}

	@Override
	@GetMapping("/to/{toId}")
	public ResponseEntity<List<FriendRequestDto>> getFriendRequestByToId(@PathVariable Long toId) {
		List<FriendRequest> friendRequests = friendRequestService.getFriendRequestByToId(toId);
		List<FriendRequestDto> friendRequestDtos = friendRequestMapper.toDtoList(friendRequests);

		return new ResponseEntity<>(friendRequestDtos, HttpStatus.OK);
	}

	@Override
	@PostMapping("/create")
	public ResponseEntity<FriendRequestDto> createFriendRequest(@RequestBody CreateFriendRequestForm input) {
		FriendRequest friendRequest = friendRequestService.createFriendRequest(input);
		FriendRequestDto friendRequestDto = friendRequestMapper.toDto(friendRequest);

		return new ResponseEntity<>(friendRequestDto, HttpStatus.OK);
	}

	@Override
	@GetMapping("/approve/{id}")
	public ResponseEntity<FriendRequestDto> approveFriendRequest(@PathVariable Long id) {
		FriendRequest friendRequest = friendRequestService.approveFriendRequest(id);
		FriendRequestDto friendRequestDto = friendRequestMapper.toDto(friendRequest);

		return new ResponseEntity<>(friendRequestDto, HttpStatus.OK);
	}

	@Override
	@GetMapping("/deny/{id}")
	public ResponseEntity<FriendRequestDto> denyFriendRequest(@PathVariable Long id) {
		FriendRequest friendRequest = friendRequestService.denyFriendRequest(id);
		FriendRequestDto friendRequestDto = friendRequestMapper.toDto(friendRequest);

		return new ResponseEntity<>(friendRequestDto, HttpStatus.OK);
	}

	@Override
	@GetMapping("/delete/{id}")
	public ResponseEntity<Long> deleteFriendRequest(@PathVariable Long id) {
		Long deletedId = friendRequestService.deleteFriendRequest(id);

		return new ResponseEntity<>(deletedId, HttpStatus.OK);
	}

	@Override
	@PostMapping("/update")
	public ResponseEntity<FriendRequestDto> updateFriendRequest(@RequestBody UpdateFriendRequestForm input) {
		FriendRequest friendRequest = friendRequestService.updateFriendRequest(input);
		FriendRequestDto friendRequestDto = friendRequestMapper.toDto(friendRequest);

		return new ResponseEntity<>(friendRequestDto, HttpStatus.OK);
	}
	
}
