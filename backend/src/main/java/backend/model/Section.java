package backend.model;

import java.util.ArrayList;

import javax.persistence.*;

@Entity
@Table(name = "course_block")
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;

    @OneToMany(cascade = { CascadeType.REMOVE }, orphanRemoval = true)
    private ArrayList<SectionBlock> sectionBlocks;

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

}
