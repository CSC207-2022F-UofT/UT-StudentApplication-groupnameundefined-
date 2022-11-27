package backend.controller.imp;

import backend.controller.FriendRequestController;
import backend.model.FriendRequest;
import backend.model.Request;
import backend.service.FriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/friend-request")
public class FriendRequestControllerImp implements FriendRequestController {

    @Autowired
    private FriendRequestService friendRequestService;

    @Override
    @GetMapping("/get-all-friend-requests")
    public ResponseEntity<List<FriendRequest>> getAllFriendRequests() {
        return null;
    }

    @Override
    @GetMapping("/get-friend-request/{id}")
    public ResponseEntity<FriendRequest> getFriendRequestById(@PathVariable Long id) {
        return null;
    }

    @Override
    @PostMapping("/create-friend-request")
    public ResponseEntity<FriendRequest> createFriendRequest(@RequestBody FriendRequest friendRequest) {
        return null;
    }

    @Override
    @PostMapping("/approve-friend-request")
    public ResponseEntity<FriendRequest> approveFriendRequest(@RequestBody Long id) {
        try{
            FriendRequest friendRequest = friendRequestService.approveFriendRequest(id);
            return new ResponseEntity<FriendRequest>(friendRequest, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PostMapping("/deny-friend-request")
    public ResponseEntity<FriendRequest> denyFriendRequest(@RequestBody Long id) {

        return null;
    }

    @Override
    @PostMapping("/delete-friend-request")
    public ResponseEntity<Long> deleteFriendRequest(@RequestBody Long id) {
        return null;
    }

    @Override
    @PostMapping("/update-friend-request")
    public ResponseEntity<FriendRequest> updateFriendRequest(@RequestBody FriendRequest friendRequest) {
        return null;
    }
}
