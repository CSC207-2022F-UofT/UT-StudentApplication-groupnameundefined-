package backend.model;

import com.google.gson.annotations.Expose;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "section_block")
public class SectionBlock extends Block {

    @Setter(AccessLevel.NONE)
    @Expose(serialize = true, deserialize = false)
    @ManyToOne
    @JoinColumn(name = "section_id", referencedColumnName = "id")
    private Section section;

    public SectionBlock() {
    }

    public SectionBlock(Section section) {
        this.section = section;
    }

    public SectionBlock(Integer startDay, Integer startMil, Integer endDay, Integer endMil, String repetition,
                        String repetitionTime) {
        super(startDay, startMil, endDay, endMil, repetition, repetitionTime);
    }

    public void setSection(Section section) {
        section.addSectionBlock(this);
        this.section = section;
    }

}
