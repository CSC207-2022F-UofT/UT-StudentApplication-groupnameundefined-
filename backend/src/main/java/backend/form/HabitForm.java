package backend.form;

import javax.validation.constraints.*;

import lombok.Getter;

public class HabitForm {

	@Getter
	public static class CreateHabitForm {

		@NotNull
		private final Long studentProfileId;

		@NotNull
		@Max(5)
		@Min(1)
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
