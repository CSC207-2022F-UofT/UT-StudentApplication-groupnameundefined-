package backend.model;

import com.google.gson.annotations.Expose;

import java.util.Set;

import javax.persistence.*;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class Block {

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

    @Expose(serialize = true, deserialize = false)
    @ManyToMany(mappedBy = "blocks")
    private Set<Timetable> timetables;

    public Block() {
    }

    public Block(Integer startDay, Integer startMil, Integer endDay, Integer endMil, String repetition) {
        this.startDay = startDay;
        this.startMil = startMil;
        this.endDay = endDay;
        this.endMil = endMil;
    }

    public Long getId() {
        return id;
    }

    public int getStartDay() {
        return startDay;
    }

    public void setStartDay(int startDay) {
        this.startDay = startDay;
    }

    public int getStartMil() {
        return startMil;
    }

    public void setStartMil(int startMil) {
        this.startMil = startMil;
    }

    public int getEndDay() {
        return endDay;
    }

    public void setEndDay(int endDay) {
        this.endDay = endDay;
    }

    public int getEndMil() {
        return endMil;
    }

    public void setEndMil(int endMil) {
        this.endMil = endMil;
    }

    public Set<Timetable> getTimetables() {
        return timetables;
    }

    public void addTimetable(Timetable timetable) {
        timetables.add(timetable);
    }

    public void removeTimetable(Timetable timetable) {
        timetables.remove(timetable);
    }

    public String getRepetition() {
        return repetition;
    }

    public void setRepetition(String repetition) {
        this.repetition = repetition;
    }

}