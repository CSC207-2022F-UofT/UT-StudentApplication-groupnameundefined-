package backend.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "student_profile")
public class StudentProfile {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Size(max = 256)
    @Column(name = "program")
    private String program;

    @Size(max = 256)
    @Column(name = "college")
    private String college;

    @Max(value = 9999)
    @Min(value = 1827)
    @Column(name = "enrolment_year")
    private Integer enrolmentYear;

    @OneToOne(cascade = {CascadeType.ALL}, mappedBy = "studentProfile")
    private Timetable timetable;

    @OneToOne
    @JoinColumn(name = "socialmediaprofile_id", referencedColumnName = "id")
    private SocialMediaProfile socialMediaProfile;

    @OneToOne(cascade = {CascadeType.ALL}, mappedBy = "studentProfile")
    private Habit habit;

    public StudentProfile() {
    }

    public StudentProfile(User user) {
        this.user = user;
    }

    public StudentProfile(String program, String college, Integer enrolmentYear) {
        this.program = program;
        this.college = college;
        this.enrolmentYear = enrolmentYear;
    }

    public void setTimetable(Timetable timetable) {
        this.timetable = timetable;
        timetable.setStudentProfile(this);
    }

}