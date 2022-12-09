package backend.model;

import com.google.gson.annotations.Expose;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class Block {

	@Setter(AccessLevel.NONE)
	@Expose(serialize = true, deserialize = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Transient
	private String type;

	@Expose(serialize = true, deserialize = true)
	@Column(name = "start_day")
	private Integer startDay;

	@Expose(serialize = true, deserialize = true)
	@Column(name = "start_mil")
	private Integer startMil;

	@Expose(serialize = true, deserialize = true)
	@Column(name = "end_day")
	private Integer endDay;

	@Expose(serialize = true, deserialize = true)
	@Column(name = "end_mil")
	private Integer endMil;

	@Expose(serialize = true, deserialize = true)
	@Column(name = "repetition")
	private String repetition;

	@Expose(serialize = true, deserialize = true)
	@Column(name = "repetition_time")
	private String repetitionTime;

	@Setter(AccessLevel.NONE)
	@Expose(serialize = true, deserialize = false)
	@ManyToMany
	@JoinTable(
			name = "timetable_x_block",
			joinColumns = @JoinColumn(name = "block_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "timetable_id", referencedColumnName = "id")
	)
	private Set<Timetable> timetables = new HashSet<>();

	@PreRemove
	private void removeGroupsFromUsers() {
		for (Timetable t : timetables) {
			t.getBlocks().remove(this);
		}
	}

	public Block() {
	}

	public Block(
			String type, Integer startDay, Integer startMil, Integer endDay, Integer endMil, String repetition,
			String repetitionTime
	) {
		this.type = type;
		this.startDay = startDay;
		this.startMil = startMil;
		this.endDay = endDay;
		this.endMil = endMil;
		this.repetition = repetition;
		this.repetitionTime = repetitionTime;
	}

	public void update(
			Integer startDay, Integer startMil, Integer endDay, Integer endMil, String repetition,
			String repetitionTime
	) {
		this.startDay = startDay;
		this.startMil = startMil;
		this.endDay = endDay;
		this.endMil = endMil;
		this.repetition = repetition;
		this.repetitionTime = repetitionTime;
	}

	public void addTimetable(Timetable timetable) {
		this.timetables.add(timetable);
		timetable.getBlocks().add(this);
	}

	public void bulkAddTimetables(Set<Timetable> timetables) {
		this.timetables.addAll(timetables);
		timetables.forEach(timetable -> timetable.getBlocks().remove(this));
	}

	public void removeTimetable(Timetable timetable) {
		this.timetables.remove(timetable);
		timetable.getBlocks().remove(this);
	}

	public void bulkRemoveTimetables(Set<Timetable> timetables) {
		this.timetables.removeAll(timetables);
		timetables.forEach(timetable -> timetable.getBlocks().remove(this));
	}
}