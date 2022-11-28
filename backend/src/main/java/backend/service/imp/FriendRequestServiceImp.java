package backend.service.imp;

import backend.form.FriendRequestForm.*;
import backend.model.FriendRequest;
import backend.model.User;
import backend.repository.FriendRequestRepository;
import backend.repository.UserRepository;
import backend.service.FriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FriendRequestServiceImp implements FriendRequestService {
    @Autowired
    private FriendRequestRepository friendRequestRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public List<FriendRequest> getAllFriendRequests() {
        List<FriendRequest> friendRequests = new ArrayList<FriendRequest>();

        friendRequests.addAll(friendRequestRepository.findAll());

        return friendRequests;
    }

    @Override
    public FriendRequest getFriendRequestById(Long id) {
        Optional<FriendRequest> friendRequest = friendRequestRepository.findById(id);
        if(friendRequest.isPresent()){
            return friendRequest.get();
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public List<FriendRequest> getFriendRequestByUserId(Long userId) {
        return friendRequestRepository.findByUserId(userId);
    }

    @Override
    public FriendRequest createFriendRequest(CreateFriendRequestForm input) {
        Optional<User> fromUser = userRepository.findById(input.getFrom());
        Optional<User> toUser = userRepository.findById(input.getTo());

        if (!(fromUser.isPresent() && toUser.isPresent())) {
            throw new EntityNotFoundException();
        }

        String message = input.getMessage();

        FriendRequest friendRequest = new FriendRequest(fromUser.get(), toUser.get(), message);

        return friendRequestRepository.save(friendRequest);
    }

    @Override
    public FriendRequest approveFriendRequest(Long id) {
        Optional<FriendRequest> friendRequest = friendRequestRepository.findById(id);
        if(friendRequest.isPresent()){
            FriendRequest _friendRequest = friendRequest.get();
            _friendRequest.setStatus("APPROVED");
            friendRequestRepository.save(_friendRequest);
            return _friendRequest;
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public FriendRequest denyFriendRequest(Long id) {
        Optional<FriendRequest> friendRequest = friendRequestRepository.findById(id);
        if(friendRequest.isPresent()){
            FriendRequest _friendRequest = friendRequest.get();
            _friendRequest.setStatus("DENIED");
            friendRequestRepository.save(_friendRequest);
            return _friendRequest;
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public Long deleteFriendRequest(Long id) {
        try {
            friendRequestRepository.deleteById(id);
            return id;
        } catch (Exception e) {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public FriendRequest updateFriendRequest(FriendRequest friendRequest) {
        Optional<FriendRequest> _friendRequest = friendRequestRepository.findById(friendRequest.getId());
        if(_friendRequest.isPresent()) {
            friendRequestRepository.save(friendRequest);
            return friendRequest;
        } else {
            throw new EntityNotFoundException();
        }
    }
}
