package backend.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class FriendRequestForm {

	@Getter
	@Setter
	public static class CreateFriendRequestForm {

		@NotNull
		private Long fromId;

		@NotNull
		private Long toId;

		@Size(min = 0, max = 128, message = "Request message must not exceed 128 characters.")
		private String message;

	}

	@Getter
	@Setter
	public static class UpdateFriendRequestForm {

		@NotNull
		private Long id;

		@Size(min = 0, max = 128, message = "Request message must not exceed 128 characters.")
		private String message;

	}

}
