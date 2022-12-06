package backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "apt_request")
public class AptRequest extends Request {

	@OneToOne(mappedBy = "aptRequest", cascade = {CascadeType.ALL}, orphanRemoval = true)
	private AptBlock aptBlock;

	public AptRequest() {
	}

	public AptRequest(User from, User to, String message) {
		super(from, to, message);
	}

}