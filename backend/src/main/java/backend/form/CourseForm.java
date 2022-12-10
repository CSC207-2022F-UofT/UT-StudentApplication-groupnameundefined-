package backend.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class CourseForm {

	@Getter
	public static class LoadCoursesForm {

		private final Boolean loadAll;

		@NotNull
		private final List<String> courses;

		public LoadCoursesForm(Boolean loadAll, List<String> courses) {
			this.loadAll = loadAll;
			this.courses = courses;
		}
	}
}
