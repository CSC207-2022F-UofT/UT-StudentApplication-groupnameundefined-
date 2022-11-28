package backend.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "friend_request")
public class FriendRequest extends Request{

    public FriendRequest(){
        super();
    }

}
