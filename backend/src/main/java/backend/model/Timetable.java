package backend.model;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "timetable")
public class Timetable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "studentprofile_id", referencedColumnName = "id")
    private StudentProfile studentProfile;

    @ManyToMany(cascade = { CascadeType.REFRESH, CascadeType.DETACH })
    @JoinTable(name = "timetable_x_block", joinColumns = @JoinColumn(name = "timetable_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "block_id", referencedColumnName = "id"))
    private Set<Block> blocks;

    public Timetable() {
    }

    public Long getId() {
        return id;
    }

    public Set<Block> getBlocks() {
        return blocks;
    }

    public void initializeBlocks() {
        blocks = new HashSet<>();
    }

    public void clearBlocks() {
        blocks = null;
    }


    public void setBlocks(Set<Block> blocks) {
        for (Block block : blocks) {
            block.addTimetable(this);
        }
        this.blocks = blocks;
    }

    public void addBlock(Block block) {
        if (blocks == null) {
            initializeBlocks();
        }
        blocks.add(block);
    }

    public void removeBlock(Block block) {
        if (blocks != null) {
            blocks.remove(block);
            if (blocks.size() == 0) {
                clearBlocks();
            }
        }
    }

    public StudentProfile getStudentProfile() {
        return studentProfile;
    }

    public void setStudentProfile(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
        studentProfile.setTimetable(this);
    }

}
