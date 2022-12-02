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
		@Min(1)
		private final Integer mbti;

		@NotNull
		@Max(5)
		@Max(1)
		private final Integer talkative;

		@NotNull
		@Max(5)
		@Min(1)
		private final Integer collaborative;

		@NotNull
		private final Boolean mbtiVisibility;

		@NotNull
		private final Boolean talkativeVisibility;

		@NotNull
		private final Boolean collaborativeVisibility;

		public CreateHabitForm(
				Long studentProfileId,
				Integer mbti,
				Integer talkative,
				Integer collaborative,
				Boolean mbtiVisibility,
				Boolean talkativeVisibility,
				Boolean collaborativeVisibility
		) {
			this.studentProfileId = studentProfileId;
			this.mbti = mbti;
			this.talkative = talkative;
			this.collaborative = collaborative;
			this.mbtiVisibility = mbtiVisibility;
			this.talkativeVisibility = talkativeVisibility;
			this.collaborativeVisibility = collaborativeVisibility;
		}

	}

}
