package backend.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "timetable")
public class Timetable {

	@Setter(AccessLevel.NONE)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "studentprofile_id", referencedColumnName = "id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private StudentProfile studentProfile;

	@Setter(AccessLevel.NONE)
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, mappedBy = "timetables")
	private Set<Block> blocks = new HashSet<>();

	public Timetable() {
	}

	public void addBlock(Block block) {
		this.blocks.add(block);
		block.getTimetables().add(this);
	}

	public void bulkAddBlocks(Set<Block> blocks) {
		this.blocks.addAll(blocks);
		blocks.forEach(block -> block.getTimetables().add(this));
	}

	public void removeBlock(Block block) {
		this.blocks.remove(block);
		block.getTimetables().remove(this);
	}

	public void bulkRemoveBlocks(Set<Block> blocks) {
		this.blocks.removeAll(blocks);
		blocks.forEach(block -> block.getTimetables().remove(this));
	}

}
