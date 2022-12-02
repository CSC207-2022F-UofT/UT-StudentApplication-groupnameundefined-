package backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "appointment_request")
public class AppointmentRequest extends Request {

	public AppointmentRequest() {
		super();
	}

	public AppointmentRequest(String message) {
		super(message);
	}

}
