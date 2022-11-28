package backend.model;

import javax.persistence.*;
import javax.validation.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.util.*;

@Entity
@Table(name = "Habits", uniqueConstraints = {@UniqueConstraint(columnNames = {"Id"})})
public class Habit{

    // Habit id, unique
    @Id
    private Long Id;

    // Correspond user id
    @NotNull
    @JoinColumn(name = "User.name")
    private String username;

    // MBTI of users: [0,16], 0 refers to Not specified.
    // MBTI encoding: refer to documents.
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
    @Column()
    @ElementCollection
    private Map<String, Integer> visibilities = new HashMap<String, Integer>();

    public Habit() {
        this.username ="";
        this.MBTI = 0;
        this.talktive = 5;
        this.collaborate = 5;
        this.visibilities.put("MBTI",1);
        this.visibilities.put("talktive",1);
        this.visibilities.put("collaborate",1);
    }


    public Habit(String username) {
        super();
        this.username = username;
        this.MBTI = 0;
        this.talktive = 5;
        this.collaborate = 5;
        this.visibilities.put("MBTI",1);
        this.visibilities.put("talktive",1);
        this.visibilities.put("collaborate",1);
    }

    public Habit(String _username, int _MBTI, int _talktive, int _collaborate, Map<String, Integer> _visibilities) {
        this.username = _username;
        this.MBTI = _MBTI;
        this.talktive = _talktive;
        this.collaborate = _collaborate;
        this.visibilities.put("MBTI",_visibilities.get("MBTI"));
        this.visibilities.put("talktive",_visibilities.get("talktive"));
        this.visibilities.put("collaborate",_visibilities.get("collaborate"));
    }

    public Habit(String username, int MBTI, int talktive, int collaborate) {
        super();
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

    public Map<String, Integer> getVisibility(){
        return this.visibilities;
    }
    public void setVisibility(Map<String, Integer> _visibilities){
        for(String newKey : _visibilities.keySet()){
            this.visibilities.put(newKey, _visibilities.get(newKey));
        }
    }

    @Override
    public String toString() {
        return "habit" + this.Id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Habit)) {
            return false;
        }

        Habit habit = (Habit)o;
        if((this.collaborate == habit.collaborate) && (this.talktive == habit.talktive) && (this.MBTI == habit.MBTI)){
            return true;
        }

        return false;
    }

}
