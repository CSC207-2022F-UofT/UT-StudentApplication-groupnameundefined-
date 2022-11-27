package backend.model;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.Collections;
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

@Entity
@Table(name = "course")
public class Course {
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

    @Expose(serialize = true, deserialize = false)
    @OneToMany(mappedBy = "course", cascade = { CascadeType.ALL }, orphanRemoval = true)
    private Set<Section> sections;

    public Course() {
    }

    public Course(String name, String code, String sectionCode, String campus) {
        this.name = name;
        this.code = code;
        this.sectionCode = sectionCode;
        this.campus = campus;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public Set<Section> getSections() {
        return sections;
    }

    public void initializeSections() {
        sections = new HashSet<>();
    }

    public void clearSections() {
        sections = null;
    }

    public void setSections(Set<Section> sections) {
        this.sections = sections;
    }

    public void addSection(Section section) {
        if (sections == null) {
            initializeSections();
        }
        sections.add(section);
        section.setCourse(this);
    }

    public void removeSection(Section section) {
        if (sections != null) {
            section.setCourse(null);
            sections.remove(section);

            if (sections.size() == 0) {
                clearSections();
            }
        }
    }

}
