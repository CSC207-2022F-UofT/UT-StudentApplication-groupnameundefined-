package backend.model;

import com.google.gson.annotations.Expose;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "course")
public class Course {

    @Setter(AccessLevel.NONE)
    @Expose(serialize = true, deserialize = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Expose(serialize = true, deserialize = true)
    @Column(name = "name")
    private String name;

    @Expose(serialize = true, deserialize = true)
    @Column(name = "code")
    private String code;

    @Expose(serialize = true, deserialize = true)
    @Column(name = "section_code")
    private String sectionCode;

    @Expose(serialize = true, deserialize = true)
    @Column(name = "campus")
    private String campus;

    @Setter(AccessLevel.NONE)
    @Expose(serialize = true, deserialize = false)
    @OneToMany(mappedBy = "course", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Set<Section> sections;

    public Course() {
    }

    public Course(String name, String code, String sectionCode, String campus) {
        this.name = name;
        this.code = code;
        this.sectionCode = sectionCode;
        this.campus = campus;
    }

    public void addSection(Section section) {
        this.sections.add(section);
        section.setCourse(this);
    }

    public void bulkAddSections(Set<Section> sections) {
        this.sections.addAll(sections);
        sections.forEach(section -> section.setCourse(this));
    }

    public void removeSection(Section section) {
        this.sections.remove(section);
    }

    public void bulkRemoveSections(Set<Section> sections) {
        this.sections.removeAll(sections);
    }

}
