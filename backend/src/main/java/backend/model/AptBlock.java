package backend.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "apt_block")
public class AptBlock extends Block {

	@Column(name = "location")
	private String location;

	@OneToOne
	@JoinColumn(name = "aptrequest_id", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private AptRequest aptRequest;

	public AptBlock() {
	}

	public AptBlock(
			Integer startDay, Integer startMil, Integer endDay, Integer endMil, String repetition,
			String repetitionTime
	) {
		super("APT", startDay, startMil, endDay, endMil, repetition, repetitionTime);
	}

	public void update(
			Integer startDay,
			Integer startMil,
			Integer endDay,
			Integer endMil,
			String repetition,
			String repetitionTime,
			String location
	) {
		super.update(startDay, startMil, endDay, endMil, repetition, repetitionTime);
		this.location = location;
	}

	public String getType() {
		return "APT";
	}
}
