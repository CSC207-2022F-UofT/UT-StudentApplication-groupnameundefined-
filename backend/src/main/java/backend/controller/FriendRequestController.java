package backend.controller;

import backend.dto.FriendRequestDto;
import backend.form.FriendRequestForm.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/friend-request")
public interface FriendRequestController {

    @PostMapping("/create")
    ResponseEntity<FriendRequestDto> createFriendRequest(@RequestBody CreateFriendRequestForm input);

    @PostMapping("/update")
    ResponseEntity<FriendRequestDto> updateFriendRequest(@RequestBody UpdateFriendRequestForm input);

    @GetMapping("/approve/{id}")
    ResponseEntity<FriendRequestDto> approveFriendRequest(@PathVariable Long id);

    @GetMapping("/deny/{id}")
    ResponseEntity<FriendRequestDto> denyFriendRequest(@PathVariable Long id);

    @GetMapping("/delete/{id}")
    ResponseEntity<Long> deleteFriendRequest(@PathVariable Long id);

    @GetMapping("/")
    ResponseEntity<List<FriendRequestDto>> getAllFriendRequests();

    @GetMapping("/{id}")
    ResponseEntity<FriendRequestDto> getFriendRequestById(@PathVariable Long id);

    @GetMapping("/from/{fromId}")
    ResponseEntity<List<FriendRequestDto>> getFriendRequestByFromId(@PathVariable Long fromId);

    @GetMapping("/to/{toId}")
    ResponseEntity<List<FriendRequestDto>> getFriendRequestByToId(@PathVariable Long toId);

}
