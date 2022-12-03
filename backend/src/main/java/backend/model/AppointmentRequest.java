package backend.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "appointment_request")
public class AppointmentRequest extends Request {
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

	public AppointmentRequest() {
	}

	public AppointmentRequest(
			User from, User to, String message, String location, Integer startDay,
			Integer startMil, Integer endDay, Integer endMil
	) {
		super(from, to, message);
		this.location = location;
		this.startDay = startDay;
		this.startMil = startMil;
		this.endDay = endDay;
		this.endMil = endMil;
	}

	public AppointmentRequest(
			User from, User to, String location, Integer startDay,
			Integer startMil, Integer endDay, Integer endMil
	) {
		super(from, to);
		this.location = location;
		this.startDay = startDay;
		this.startMil = startMil;
		this.endDay = endDay;
		this.endMil = endMil;
	}

	public void updateAppointmentRequest(
			String message, String location, Integer startDay,
			Integer startMil, Integer endDay, Integer endMil
	) {
		this.setMessage(message);
		this.location = location;
		this.startDay = startDay;
		this.startMil = startMil;
		this.endDay = endDay;
		this.endMil = endMil;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getStartDay() {
		return startDay;
	}

	public void setStartDay(Integer startDay) {
		this.startDay = startDay;
	}

	public Integer getStartMil() {
		return startMil;
	}

	public void setStartMil(Integer startMil) {
		this.startMil = startMil;
	}

	public Integer getEndDay() {
		return endDay;
	}

	public void setEndDay(Integer endDay) {
		this.endDay = endDay;
	}

	public Integer getEndMil() {
		return endMil;
	}

	public void setEndMil(Integer endMil) {
		this.endMil = endMil;
	}
}