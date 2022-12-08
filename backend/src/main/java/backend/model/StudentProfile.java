package backend.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;

	@Column(name = "program")
	private String program;

	@Column(name = "college")
	private String college;

	@Column(name = "enrolment_year")
	private Integer enrolmentYear;

	@ElementCollection(fetch = FetchType.EAGER)
	@Column(name = "course_codes")
	private Set<String> courseCodes = new HashSet<>();

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

	public void addCourse(String courseCode) {
		this.courseCodes.add(courseCode);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (!(o instanceof StudentProfile)) {
			return false;
		}

		return this.id.equals(((StudentProfile) o).getId());
	}

}