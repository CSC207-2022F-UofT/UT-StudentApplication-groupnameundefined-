package backend.form;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

public class AppointmentRequestForm {

	@Getter
	@Setter
	public static class CreateAppointmentRequestForm {

		@NotNull
		private Long from;

		@NotNull
		private Long to;

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
	}

	@Getter
	@Setter
	public static class UpdateAppointmentRequestForm {

		@NotNull
		private Long id;

		private String message;
		private String location;
		private Integer startDay;
		private Integer startMil;
		private Integer endDay;
		private Integer endMil;
	}
}