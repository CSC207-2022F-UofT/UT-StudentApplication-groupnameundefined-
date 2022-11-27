package backend.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "student_profile")
public class StudentProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Size(max = 256, message = "Program name should not exceed 256 characters.")
    @Column(name = "program")
    private String program;

    @Size(max = 256, message = "College name should not exceed 256 characters.")
    @Column(name = "college")
    private String college;

    @Max(value = 9999, message = "Invalid enrolment year")
    @Min(value = 1827, message = "Invalid enrolment year")
    @Column(name = "enrolment_year")
    private Integer enrolmentYear;

    @OneToOne(cascade = { CascadeType.ALL }, mappedBy = "studentProfile")
    private Timetable timetable;

    @OneToOne
    @JoinColumn(name = "socialmediaprofile_id", referencedColumnName = "id")
    private SocialMediaProfile socialMediaProfile;

    public StudentProfile() {
    }

    public StudentProfile(User user) {
        this.user = user;
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

    public Timetable getTimetable() {
        return timetable;
    }

    public void setTimetable(Timetable timetable) {
        this.timetable = timetable;
    }

    public void removeTimetable() {
        timetable = null;
    }

    public SocialMediaProfile getSocialMediaProfile() {
        return socialMediaProfile;
    }

    public void setSocialMediaProfile(SocialMediaProfile socialMediaProfile) {
        this.socialMediaProfile = socialMediaProfile;
    }
}