package backend.controller.imp;

import backend.controller.FriendRequestController;
import backend.form.FriendRequestForm.*;
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

    @Autowired
    private FriendRequestService friendRequestService;

    @Autowired
    Logger logger;

    @Override
    @GetMapping("/")
    public ResponseEntity<List<FriendRequest>> getAllFriendRequests() {
        try{
            List<FriendRequest> allFriendRequests = friendRequestService.getAllFriendRequests();
            return new ResponseEntity<List<FriendRequest>>(allFriendRequests, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<FriendRequest> getFriendRequestById(@PathVariable Long id) {
        try{
            FriendRequest friendRequest = friendRequestService.getFriendRequestById(id);
            return new ResponseEntity<FriendRequest>(friendRequest, HttpStatus.OK);
        } catch (Exception e){
            logger.error("error", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FriendRequest>> getFriendRequestByUserId(@PathVariable Long userId) {
        try{
            List<FriendRequest> allFriendRequests = friendRequestService.getFriendRequestByUserId(userId);
            return new ResponseEntity<List<FriendRequest>>(allFriendRequests, HttpStatus.OK);
        } catch (Exception e){
            logger.error("error", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PostMapping("/create")
    public ResponseEntity<FriendRequest> createFriendRequest(@RequestBody CreateFriendRequestForm input) {
        try{
            FriendRequest newFriendRequest = friendRequestService.createFriendRequest(input);
            return new ResponseEntity<FriendRequest>(newFriendRequest, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PostMapping("/approve")
    public ResponseEntity<FriendRequest> approveFriendRequest(@RequestBody Long id) {
        try{
            FriendRequest friendRequest = friendRequestService.approveFriendRequest(id);
            return new ResponseEntity<FriendRequest>(friendRequest, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PostMapping("/deny")
    public ResponseEntity<FriendRequest> denyFriendRequest(@RequestBody Long id) {
        try{
            FriendRequest friendRequest = friendRequestService.denyFriendRequest(id);
            return new ResponseEntity<FriendRequest>(friendRequest, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PostMapping("/delete")
    public ResponseEntity<Long> deleteFriendRequest(@RequestBody Long id) {
        try{
            Long deletedId = friendRequestService.deleteFriendRequest(id);
            return new ResponseEntity<Long>(deletedId, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PostMapping("/update")
    public ResponseEntity<FriendRequest> updateFriendRequest(@RequestBody FriendRequest friendRequest) {
        try{
            FriendRequest updatedFriendRequest = friendRequestService.updateFriendRequest(friendRequest);
            return new ResponseEntity<FriendRequest>(updatedFriendRequest, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
