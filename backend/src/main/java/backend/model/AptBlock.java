package backend.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "apt_block")
public class AptBlock extends Block {

	public AptBlock() {
	}

	public AptBlock(
			Integer startDay, Integer startMil, Integer endDay, Integer endMil, String repetition,
			String repetitionTime
	) {
		super(startDay, startMil, endDay, endMil, repetition, repetitionTime);
	}
	
}
