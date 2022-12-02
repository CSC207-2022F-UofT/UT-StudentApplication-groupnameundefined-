package backend.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class FriendRequestForm {

	@Getter
	public static class CreateFriendRequestForm {

		@NotNull
		private final Long fromId;

		@NotNull
		private final Long toId;

		@Size(min = 0, max = 128, message = "Request message must not exceed 128 characters.")
		private final String message;

		public CreateFriendRequestForm(Long fromId, Long toId, String message) {
			this.fromId = fromId;
			this.toId = toId;
			this.message = message;
		}

	}

	@Getter
	public static class UpdateFriendRequestForm {

		@NotNull
		private final Long id;

		@Size(min = 0, max = 128, message = "Request message must not exceed 128 characters.")
		private final String message;

		public UpdateFriendRequestForm(Long id, String message) {
			this.id = id;
			this.message = message;
		}

	}

}
