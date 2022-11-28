package backend.form;

import lombok.Getter;
import lombok.Setter;

public class FriendRequestForm {

    @Getter
    @Setter
    public static class CreateFriendRequestForm {

        private Long from;
        private Long to;
        private String message;

    }
}
