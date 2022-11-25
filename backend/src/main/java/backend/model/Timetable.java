package backend.model;

import java.util.ArrayList;
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
    @JoinTable(name = "timetable_block", joinColumns = @JoinColumn(name = "timetable_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "block_id", referencedColumnName = "id"))
    private Set<Block> blocks;

    public Timetable() {
    }

    public Long getId() {
        return id;
    }

    public Set<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(Set<Block> blocks) {
        for (Block block : blocks) {
            block.addTimetable(this);
        }
        this.blocks = blocks;
    }

    public void addBlock(Block block) {
        blocks.add(block);
        block.addTimetable(this);
    }

    public void removeBlock(Block block) {
        blocks.remove(block);
        block.removeTimetable(this);
    }
}
