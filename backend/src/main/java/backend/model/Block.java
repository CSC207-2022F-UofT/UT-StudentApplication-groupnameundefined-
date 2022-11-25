package backend.model;

import java.util.ArrayList;
import java.util.Set;

import javax.persistence.*;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "start_day")
    private Integer startDay;

    @Column(name = "start_mil")
    private Integer startMil;

    @Column(name = "end_day")
    private Integer endDay;

    @Column(name = "end_mil")
    private Integer endMil;

    @ManyToMany(mappedBy = "blocks")
    private Set<Timetable> timetables;

    public Block() {
    }

    public Block(int startDay, int startMil, int endDay, int endMil) {
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

}