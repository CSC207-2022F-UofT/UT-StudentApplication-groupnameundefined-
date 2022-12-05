package backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "appointment_request")
public class AptRequest extends Request {
	@Column(name = "location")
	private String location;

	@Column(name = "start_day")
	private Integer startDay;

	@Column(name = "start_mil")
	private Integer startMil;

	@Column(name = "end_day")
	private Integer endDay;

	@Column(name = "end_mil")
	private Integer endMil;

	@Column(name = "repetition")
	private String repetition;

	@Column(name = "repetitionTime")
	private String repetitionTime;

	public AptRequest() {
	}

	public AptRequest(
			User from, User to, String message, String location, Integer startDay,
			Integer startMil, Integer endDay, Integer endMil, String repetition, String repetitionTime
	) {
		super(from, to, message);
		this.location = location;
		this.startDay = startDay;
		this.startMil = startMil;
		this.endDay = endDay;
		this.endMil = endMil;
		this.repetition = repetition;
		this.repetitionTime = repetitionTime;
	}


	public void updateAppointmentRequest(
			String message, String location, Integer startDay,
			Integer startMil, Integer endDay, Integer endMil, String repetition, String repetitionTime
	) {
		this.setMessage(message);
		this.location = location;
		this.startDay = startDay;
		this.startMil = startMil;
		this.endDay = endDay;
		this.endMil = endMil;
		this.repetition = repetition;
		this.repetitionTime = repetitionTime;
	}

}