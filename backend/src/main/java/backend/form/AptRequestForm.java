package backend.form;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AptRequestForm {

	@Getter
	@Setter
	public static class CreateAptRequestForm {

		@NotNull
		private Long fromId;

		@NotNull
		private Long toId;

		@NotNull
		@Size(min = 0, max = 128, message = "Request message must not exceed 128 characters.")
		private String message;

		@NotNull
		private String location;

		@NotNull
		private Integer startDay;

		@NotNull
		private Integer startMil;

		@NotNull
		private Integer endDay;

		@NotNull
		private Integer endMil;

		private String repetition;

		private String repetitionTime;

	}

	@Getter
	@Setter
	public static class UpdateAptRequestForm {

		@NotNull
		private Long id;

		@NotNull
		@Size(min = 0, max = 128, message = "Request message must not exceed 128 characters.")
		private String message;

		@NotNull
		@Size(min = 0, max = 64, message = "Request message must not exceed 128 characters.")
		private String location;

		@NotNull
		private Integer startDay;

		@NotNull
		private Integer startMil;

		@NotNull
		private Integer endDay;

		@NotNull
		private Integer endMil;

		@NotEmpty
		private String repetition;

		@NotEmpty
		private String repetitionTime;

	}
}