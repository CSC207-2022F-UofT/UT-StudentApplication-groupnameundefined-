package backend.service.imp;

import backend.model.FriendRequest;
import backend.repository.FriendRequestRepository;
import backend.service.FriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FriendRequestServiceImp implements FriendRequestService {
    @Autowired
    private FriendRequestRepository friendRequestRepository;


    @Override
    public List<FriendRequest> getAllFriendRequests() {
        List<FriendRequest> friendRequests = new ArrayList<FriendRequest>();

        friendRequestRepository.findAll().forEach(friendRequests::add);

        return friendRequests;
    }

    @Override
    public Optional<FriendRequest> getFriendRequestById(Long id) {
        Optional<FriendRequest> friendRequest = friendRequestRepository.findById(id);
        return friendRequest;
    }

    @Override
    public FriendRequest createFriendRequest(FriendRequest friendRequest) {
        FriendRequest _friendRequest = friendRequestRepository.save(friendRequest);
        return _friendRequest;
    }

    @Override
    public FriendRequest approveFriendRequest(Long id) {
        Optional<FriendRequest> friendRequest = friendRequestRepository.findById(id);
        FriendRequest _friendRequest = friendRequest.get();
        _friendRequest.setStatus("APPROVED");
        friendRequestRepository.save(_friendRequest);
        return _friendRequest;
    }

    @Override
    public FriendRequest denyFriendRequest(Long id) {
        return null;
    }

    @Override
    public Long deleteFriendRequest(Long id) {
        return null;
    }

    @Override
    public FriendRequest updateFriendRequest(FriendRequest friendRequest) {
        return null;
    }
}
