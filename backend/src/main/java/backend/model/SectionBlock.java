package backend.model;

import com.google.gson.annotations.Expose;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "section_block")
public class SectionBlock extends Block {

    @Expose(serialize = true, deserialize = false)
    @ManyToOne
    @JoinColumn(name = "section_id", referencedColumnName = "id")
    private Section section;

    public SectionBlock() {
    }

    public SectionBlock(Section section) {
        this.section = section;
    }

    public SectionBlock(Integer startDay, Integer startMil, Integer endDay, Integer endMil,
            String repetition, String repetitionTime) {
        super(startDay, startMil, endDay, endMil, repetition, repetitionTime);
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

}
