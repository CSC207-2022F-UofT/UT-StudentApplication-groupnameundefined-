package backend.model;

import java.util.ArrayList;

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
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "section_code")
    private String sectionCode;

    @Column(name = "campus")
    private String campus;

    @OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true)
    private ArrayList<Section> sections;

    public Course(String name, String code, String sectionCode, String campus, ArrayList<Section> sections) {
        this.name = name;
        this.code = code;
        this.sectionCode = sectionCode;
        this.campus = campus;
        this.sections = sections;
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

    public ArrayList<Section> getSections() {
        return sections;
    }

    public void setSections(ArrayList<Section> sections) {
        for (Section section : sections) {
            section.setCourse(this);
        }
        for (Section section : this.sections) {
            section.setCourse(null);
        }
        this.sections = sections;
    }

    public void addSection(Section section) {
        sections.add(section);
        section.setCourse(this);
    }

    public void removeSection(Section section) {
        section.setCourse(null);
        sections.remove(section);
    }

}
