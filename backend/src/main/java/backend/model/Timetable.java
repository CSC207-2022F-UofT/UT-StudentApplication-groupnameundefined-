package backend.model;

import java.util.ArrayList;

import javax.persistence.*;

@Entity
@Table(name = "timetable")
public class Timetable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(cascade = { CascadeType.REFRESH, CascadeType.DETACH })
    @JoinTable(name = "timetable_block", joinColumns = @JoinColumn(name = "timetable_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "block_id", referencedColumnName = "id"))
    private ArrayList<Block> blocks;

    @OneToOne
    @JoinColumn(name = "studentprofile_id", referencedColumnName = "id")
    private StudentProfile studentProfile;

    public Timetable() {
    }

    public Long getId() {
        return id;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(ArrayList<Block> blocks) {
        for (Block block : blocks) {
            if (!block.getTimetables().contains(this)) {
                block.addTimetable(this);
            }
        }
        this.blocks = blocks;
    }

    public void addBlock(Block block) {
        if (!blocks.contains(block)) {
            blocks.add(block);
        }

        if (!block.getTimetables().contains(this)) {
            block.addTimetable(this);
        }
    }

    public void removeBlock(Block block) {
        blocks.remove(block);
        block.removeTimetable(this);
    }
}
