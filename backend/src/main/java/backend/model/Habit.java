package backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "habit")
public class Habit {

    // Habit id, unique
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "studentprofile_id", referencedColumnName = "id", nullable = false)
    private StudentProfile studentProfile;

    // MBTI of users: [0,16], 0 refers to Not specified.
    // MBTI encoding: refer to documents.
    @NotNull
    @Column(name = "mbti", columnDefinition = "integer default ")
    private Integer mbti;

    // The user's self reported talkativeness(extrovert of introvert), rating 1~5
    @NotNull
    @Min(value = 1, message = "talkative: please choose a scale between 1 and 5(inclusive)")
    @Max(value = 5, message = "talkative: please choose a scale between 1 and 5(inclusive)")
    @Column(name = "talkative")
    private Integer talkative;

    // The user's willingness to collaborate with others, rating 1~5
    @NotNull
    @Min(value = 1, message = "Collaborative: please choose a scale between 1 and 5(inclusive)")
    @Max(value = 5, message = "Collaborative: please choose a scale between 1 and 5(inclusive)")
    @Column(name = "collaborative")
    private Integer collaborative;

    // Visibility of talkative, Collaborate, MBTI
    @OneToOne(cascade = {CascadeType.ALL}, mappedBy = "habit")
    private HabitVisibility visibility;

    public Habit() {
    }

    public Habit(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;

        this.mbti = 0;
        this.talkative = 5;
        this.collaborative = 5;

        HabitVisibility habitVisibility = new HabitVisibility();
        habitVisibility.setHabit(this);
        this.visibility = habitVisibility;
    }

    public Habit(StudentProfile studentProfile, int mbti, int talkative, int collaborative) {
        this.studentProfile = studentProfile;
        studentProfile.setHabit(this);

        this.mbti = mbti;
        this.talkative = talkative;
        this.collaborative = collaborative;
    }

    public Habit(StudentProfile studentProfile, int mbti, int talkative, int collaborative,
                 HabitVisibility habitVisibility) {
        this.studentProfile = studentProfile;
        studentProfile.setHabit(this);

        this.mbti = mbti;
        this.talkative = talkative;
        this.collaborative = collaborative;

        this.visibility = habitVisibility;
        habitVisibility.setHabit(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Habit)) {
            return false;
        }

        Habit habit = (Habit) o;
        return (Objects.equals(this.collaborative, habit.collaborative)) && (Objects.equals(this.talkative,
                habit.talkative)) && (Objects.equals(this.mbti, habit.mbti));
    }

}
