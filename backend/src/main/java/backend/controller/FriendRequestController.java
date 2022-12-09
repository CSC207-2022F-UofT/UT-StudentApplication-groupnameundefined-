package backend.controller;

import backend.dto.FriendRequestDto;
import backend.form.FriendRequestForm.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/friend-request")
public interface FriendRequestController {

	@GetMapping("/")
	ResponseEntity<List<FriendRequestDto>> getAllFriendRequests();

	@GetMapping("/{id}")
	ResponseEntity<FriendRequestDto> getFriendRequestById(@PathVariable Long id);

	@GetMapping("/from/{fromId}")
	ResponseEntity<List<FriendRequestDto>> getFriendRequestByFromId(@PathVariable Long fromId);

	@GetMapping("/to/{toId}")
	ResponseEntity<List<FriendRequestDto>> getFriendRequestByToId(
			@PathVariable Long toId,
			@RequestParam @NotBlank @Pattern(regexp = "PENDING|APPROVED|DENIED") String status
	);

	@PostMapping("/create")
	ResponseEntity<FriendRequestDto> createFriendRequest(@RequestBody @Valid CreateFriendRequestForm input);

	@PostMapping("/update")
	ResponseEntity<FriendRequestDto> updateFriendRequest(@RequestBody @Valid UpdateFriendRequestForm input);

	@GetMapping("/approve/{id}")
	ResponseEntity<FriendRequestDto> approveFriendRequest(@PathVariable Long id);

	@GetMapping("/deny/{id}")
	ResponseEntity<FriendRequestDto> denyFriendRequest(@PathVariable Long id);

	@GetMapping("/delete/{id}")
	ResponseEntity<Long> deleteFriendRequest(@PathVariable Long id);


}
