package backend.form;

import java.io.File;

import javax.validation.constraints.*;

import backend.model.StudentProfile;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

public class HabitForm {

	@Getter
	public static class CreateHabitForm {

		@NotNull
		private final Long studentProfileId;

		@NotNull
		@Max(5)
		@Max(1)
		private final Integer talkative;

		@NotNull
		@Max(5)
		@Min(1)
		private final Integer collaborative;

		public CreateHabitForm(
				Long studentProfileId,
				Integer talkative,
				Integer collaborative
		) {
			this.studentProfileId = studentProfileId;
			this.talkative = talkative;
			this.collaborative = collaborative;
		}

	}

	@Getter
	public static class UpdateHabitForm {

		@NotNull
		private final Long id;

		@NotNull
		@Max(5)
		@Max(1)
		private final Integer talkative;

		@NotNull
		@Max(5)
		@Min(1)
		private final Integer collaborative;

		public UpdateHabitForm(
				Long id,
				Integer talkative,
				Integer collaborative
		) {
			this.id = id;
			this.talkative = talkative;
			this.collaborative = collaborative;
		}

	}
}
