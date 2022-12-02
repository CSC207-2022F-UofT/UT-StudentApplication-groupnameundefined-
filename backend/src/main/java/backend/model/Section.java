package backend.model;

import com.google.gson.annotations.Expose;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "section")
public class Section {

	@Setter(AccessLevel.NONE)
	@Expose(serialize = true, deserialize = false)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Expose(serialize = true, deserialize = true)
	@ManyToOne
	@JoinColumn(name = "course_id", referencedColumnName = "id")
	private Course course;

	@Expose(serialize = true, deserialize = true)
	@Column(name = "name")
	private String name;

	@Setter(AccessLevel.NONE)
	@Expose(serialize = true, deserialize = false)
	@OneToMany(mappedBy = "section", cascade = {CascadeType.ALL}, orphanRemoval = true)
	private Set<SectionBlock> sectionBlocks = new HashSet<>();

	public Section() {
	}

	public Section(String name) {
		this.name = name;
	}

	public void addSectionBlock(SectionBlock sectionBlock) {
		this.sectionBlocks.add(sectionBlock);
		sectionBlock.setSection(this);
	}

	public void bulkAddSectionBlocks(Set<SectionBlock> sectionBlocks) {
		this.sectionBlocks.addAll(sectionBlocks);
		sectionBlocks.forEach(sectionBlock -> sectionBlock.setSection(this));
	}

	public void removeSectionBlock(SectionBlock sectionBlock) {
		this.sectionBlocks.remove(sectionBlock);
	}

	public void bulkRemoveSectionBlocks(Set<SectionBlock> sectionBlocks) {
		this.sectionBlocks.removeAll(sectionBlocks);
	}

}
