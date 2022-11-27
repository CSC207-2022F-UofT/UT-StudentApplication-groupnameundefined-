package backend.model;

import com.google.gson.annotations.Expose;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "section")
public class Section {
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

    @Expose(serialize = true, deserialize = false)
    @OneToMany(mappedBy = "section", cascade = { CascadeType.ALL }, orphanRemoval = true)
    private Set<SectionBlock> sectionBlocks;

    public Section() {
    }

    public Section(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Set<SectionBlock> getSectionBlocks() {
        return sectionBlocks;
    }

    public void initializeSectionBlocks() {
        sectionBlocks = new HashSet();
    }

    public void clearSectionBlocks() {
        sectionBlocks = null;
    }

    public void setSectionBlocks(Set<SectionBlock> sectionBlocks) {
        if (this.sectionBlocks != null) {
            for (SectionBlock sectionBlock : sectionBlocks) {
                sectionBlock.setSection(this);
            }

            for (SectionBlock sectionBlock : this.sectionBlocks) {
                sectionBlock.setSection(null);
            }
        }

        this.sectionBlocks = sectionBlocks;
    }

    public void addSectionBlock(SectionBlock sectionBlock) {
        if (sectionBlocks == null) {
            initializeSectionBlocks();
        }
        sectionBlocks.add(sectionBlock);
        sectionBlock.setSection(this);
    }

    public void removeSectionBlock(SectionBlock sectionBlock) {
        if (sectionBlocks != null) {
            sectionBlock.setSection(this);
            sectionBlocks.remove(sectionBlock);

            if (sectionBlocks.size() == 0) {
                clearSectionBlocks();
            }
        }
    }

}
