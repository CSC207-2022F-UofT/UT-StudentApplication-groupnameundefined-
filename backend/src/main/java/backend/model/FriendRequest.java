package backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "friend_request")
public class FriendRequest extends Request {

	public FriendRequest() {
		super();
	}

	public FriendRequest(User fromUser, User toUser, String message) {
		super(fromUser, toUser, message);
	}

}
