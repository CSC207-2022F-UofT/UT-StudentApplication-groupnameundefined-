package backend.model;

import java.util.ArrayList;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "section")
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;

    @OneToMany(mappedBy = "section", cascade = { CascadeType.ALL }, orphanRemoval = true)
    private Set<SectionBlock> sectionBlocks;

    public Section(Course course) {
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setSectionBlocks(Set<SectionBlock> sectionBlocks) {
        for (SectionBlock sectionBlock : sectionBlocks) {
            sectionBlock.setSection(this);
        }

        for (SectionBlock sectionBlock : this.sectionBlocks) {
            sectionBlock.setSection(null);
        }

        this.sectionBlocks = sectionBlocks;
    }

    public void addSectionBlock(SectionBlock sectionBlock) {
        sectionBlock.setSection(this);
        this.sectionBlocks.add(sectionBlock);
    }

    public void removeSectionBlock(SectionBlock sectionBlock) {
        sectionBlock.setSection(null);
        this.sectionBlocks.remove(sectionBlock);
    }

}
