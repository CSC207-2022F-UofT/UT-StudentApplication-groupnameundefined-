package backend.model;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "student_profile")
public class StudentProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "program")
    private String program;

    @Column(name = "college")
    private String college;

    @Column(name = "enrolment_year")
    private Integer enrolmentYear;

    @ElementCollection
    @Column(name = "courses")
    private ArrayList<String> courses;

//    @Column(name = "habits")
//    private Habits habits;

    // this variable is not contained in "student_profile" table
    @ManyToMany
    @JoinTable(
            name = "timetable",
            joinColumns = @JoinColumn(name = "studentprofile_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "block_id", referencedColumnName = "id")
    )
    private ArrayList<Block> timetable;

    @OneToOne
    @JoinColumn(name = "socialmediaprofile_id", referencedColumnName = "id")
    private SocialMediaProfile socialMediaProfile;

    public StudentProfile() {
    }

    public StudentProfile(User user) {
        this.user = user;
        this.program = program;
        this.college = college;
        this.enrolmentYear = enrolmentYear;
    }

    public StudentProfile(User user, String program, String college, Integer enrolmentYear) {
        this.user = user;
        this.program = program;
        this.college = college;
        this.enrolmentYear = enrolmentYear;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public Integer getEnrolmentYear() {
        return enrolmentYear;
    }

    public void setEnrolmentYear(Integer enrolmentYear) {
        this.enrolmentYear = enrolmentYear;
    }

    public ArrayList<String> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<String> courses) {
        this.courses = courses;
    }

    public ArrayList<Block> getTimetable() {
        return timetable;
    }

    public void setTimetable(ArrayList<Block> timetable) {
        this.timetable = timetable;
    }

    public SocialMediaProfile getSocialMediaProfile() {
        return socialMediaProfile;
    }

    public void setSocialMediaProfile(SocialMediaProfile socialMediaProfile) {
        this.socialMediaProfile = socialMediaProfile;
    }
}
