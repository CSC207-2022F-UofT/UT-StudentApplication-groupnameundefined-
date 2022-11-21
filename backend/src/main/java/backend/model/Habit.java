package backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.*;
import java.util.*;

@Entity
@Table(name = "habits", uniqueConstraints = {@UniqueConstraint(columnNames = {"habit_id"})})
public class Habit{

    // Habit id, unique
    @Id
    @NotNull
    private Long habit_id;

    // Correspond user id
    @NotNull
    @JoinColumn(name = "User.username")
    private String username;

    // MBTI of users: [0,16], 0 refers to Not specified.
    @NotNull
    @Column()
    private int MBTI;

    // The user's self reported talktiveness(extrovert of introvert), rating 1~5
    @NotNull
    @Min(value=1, message="Talktive: please choose a scale between 1 and 5(inclusive)")
    @Max(value=5, message="Talktive: please choose a scale between 1 and 5(inclusive)")
    private int talktive;

    // The user's willingness to collaborate with others, rating 1~5
    @NotNull
    @Min(value=1, message="Collaborative: please choose a scale between 1 and 5(inclusive)")
    @Max(value=5, message="Collaborative: please choose a scale between 1 and 5(inclusive")
    private int collaborate;

    // Visibility of Talktive, Collaborate, MBTI
    @NotNull
    @Column()
    private List<Boolean> visibilities = new ArrayList<Boolean>(Arrays.asList(new Boolean[10]));
    Collections.fill(list, Boolean.TRUE);

    public Habit(String username) {
        this.username = username;
        this.MBTI = 0;
        this.talktive = 5;
        this.collaborate = 5;
    }

    public Habit(String username, int MBTI, int talktive, int collaborate) {
        this.username = username;
        this.MBTI = MBTI;
        this.talktive = 5;
        this.collaborate = 5;
    }

    public String getUsername() { return this.username; }
    public void setUsername(String _username) { this.username = _username; }

    public int getTalktive(){ return this.talktive; }
    public void setTalktive(int _talktive){ this.talktive = _talktive; }

    public int getCollaborate(){ return this.collaborate; }
    public void setCollaborate(int _collaborate){ this.collaborate = _collaborate; }

    public int getMBTI(){ return this.MBTI; }
    public void setMBTI(int _MBTI){ this.MBTI = _MBTI; }

    public List<Boolean> getVisibility(){ return this.visibilities; }
    public void set(List<Boolean> _visibilities){
        this.visibilities = new ArrayList<String>(_visibilities);
    }

    @Override
    public String toString() {
        return this.username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Habit)) {
            return false;
        }

        Habit habit = (Habit) habit;
        if((this.collaborate == habit.collaborate) && (this.talktive == habit.talktive) && (this.MBTI == habit.MBIT)){
            return true;
        }

        return false;
    }

}
