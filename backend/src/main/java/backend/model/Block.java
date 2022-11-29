package backend.model;

import com.google.gson.annotations.Expose;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import javax.persistence.*;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class Block {

    @Setter(AccessLevel.NONE)
    @Expose(serialize = true, deserialize = true)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Expose(serialize = true, deserialize = true)
    @Column(name = "start_day")
    private Integer startDay;

    @Expose(serialize = true, deserialize = true)
    @Column(name = "start_mil")
    private Integer startMil;

    @Expose(serialize = true, deserialize = true)
    @Column(name = "end_day")
    private Integer endDay;

    @Expose(serialize = true, deserialize = true)
    @Column(name = "end_mil")
    private Integer endMil;

    @Expose(serialize = true, deserialize = true)
    @Column(name = "repetition")
    private String repetition;

    @Expose(serialize = true, deserialize = true)
    @Column(name = "repetition_time")
    private String repetitionTime;

    @Expose(serialize = true, deserialize = false)
    @ManyToMany(mappedBy = "blocks")
    private Set<Timetable> timetables;

    public Block() {
    }

    public Block(Integer startDay, Integer startMil, Integer endDay, Integer endMil, String repetition,
                 String repetitionTime) {
        this.startDay = startDay;
        this.startMil = startMil;
        this.endDay = endDay;
        this.endMil = endMil;
        this.repetition = repetition;
        this.repetitionTime = repetitionTime;
    }

    public void addTimetable(Timetable timetable) {
        timetables.add(timetable);
    }
}