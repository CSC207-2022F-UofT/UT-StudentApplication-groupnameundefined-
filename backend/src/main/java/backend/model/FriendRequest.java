package backend.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "friend_request")
public class FriendRequest extends Request{

    public FriendRequest(){
        super();
    }

    public FriendRequest(User from, User to) {
        super(from, to);
    }

    public FriendRequest(User from, User to, String message) {
        super(from, to, message);
    }
}
