package backend.model;

import com.google.gson.annotations.Expose;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "section_block")
public class SectionBlock extends Block {

	@Expose(serialize = true, deserialize = false)
	@ManyToOne
	@JoinColumn(name = "section_id", referencedColumnName = "id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Section section;

	public SectionBlock() {
	}

	public SectionBlock(
			Integer startDay, Integer startMil, Integer endDay, Integer endMil, String repetition,
			String repetitionTime
	) {
		super("SEC", startDay, startMil, endDay, endMil, repetition, repetitionTime);
	}

	public String getType() {
		return "SEC";
	}

}
