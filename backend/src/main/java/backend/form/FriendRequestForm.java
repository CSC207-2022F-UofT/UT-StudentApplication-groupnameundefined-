package backend.form;

import lombok.Getter;
import lombok.Setter;

public class FriendRequestForm {

    @Getter
    @Setter
    public static class CreateFriendRequestForm {

        private Long fromId;
        private Long toId;
        private String message;

    }
}
