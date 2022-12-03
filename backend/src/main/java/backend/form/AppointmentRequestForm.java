package backend.form;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

public class AppointmentRequestForm {

	@Getter
	@Setter
	public static class CreateAppointmentRequestForm {
		private Long from;
		private Long to;
		private String message;
		private String location;
		private Integer startDay;
		private Integer startMil;
		private Integer endDay;
		private Integer endMil;
	}

	@Getter
	@Setter
	public static class UpdateAppointmentRequestForm {
		private Long id;
		private String message;
		private String location;
		private Integer startDay;
		private Integer startMil;
		private Integer endDay;
		private Integer endMil;
	}
}