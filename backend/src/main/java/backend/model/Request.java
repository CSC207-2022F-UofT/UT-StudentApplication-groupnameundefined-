package backend.model;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "request")
public class Request {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// status in {PENDING, APPROVED, DENIED}
	@Column(name = "status")
	private String status;

	@ManyToOne
	@NotNull
	@JoinColumn(name = "from_id", referencedColumnName = "id")
	private User from;

	@ManyToOne
	@NotNull
	@JoinColumn(name = "to_id", referencedColumnName = "id")
	private User to;

	@Column(name = "message")
	private String message;

	@Column(name = "timestamp")
	private Timestamp timestamp;

	public Request() {
		// Default status without explicitly addressing is PENDING
		this.status = "PENDING";
		// Keep track of the time when the request is created.
		this.timestamp = new Timestamp(System.currentTimeMillis());
	}

	public Request(String message) {
		this.message = message;
		// Default status without explicitly addressing is PENDING
		this.status = "PENDING";
		// Keep track of the time when the request is created.
		this.timestamp = new Timestamp(System.currentTimeMillis());
	}

	@Override
	public String toString() {
		return "Request " + this.id + ": from " + this.from.getName()
				+ "to " + this.to.getName() + " is " + this.status;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Request request)) {
			return false;
		}

		return this.id.equals(request.id);
	}
}
