package backend.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "student_profile")
public class StudentProfile {

	@Setter(AccessLevel.NONE)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	private User user;

	@Column(name = "program")
	private String program;

	@Column(name = "college")
	private String college;

	@Column(name = "enrolment_year")
	private Integer enrolmentYear;

	@Setter(AccessLevel.NONE)
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
			name = "studentprofile_courses",
			joinColumns = @JoinColumn(name = "studentprofile_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id")
	)
	private Set<Course> courses = new HashSet<>();

	@OneToOne(cascade = {CascadeType.ALL}, mappedBy = "studentProfile", orphanRemoval = true)
	private Timetable timetable;

	@OneToOne(cascade = {CascadeType.ALL}, mappedBy = "studentProfile", orphanRemoval = true)
	private SocialMediaProfile socialMediaProfile;

	@OneToOne(cascade = {CascadeType.ALL}, mappedBy = "studentProfile", orphanRemoval = true)
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

	public void addCourse(Course course) {
		this.courses.add(course);
		course.getStudentProfiles().add(this);
	}

}