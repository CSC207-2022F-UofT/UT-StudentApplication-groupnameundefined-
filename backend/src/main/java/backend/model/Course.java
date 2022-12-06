package backend.model;

import com.google.gson.annotations.Expose;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "course")
public class Course {

	@Setter(AccessLevel.NONE)
	@Expose(serialize = true, deserialize = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Expose(serialize = true, deserialize = true)
	@Column(name = "name")
	private String name;

	@Expose(serialize = true, deserialize = true)
	@Column(name = "code")
	private String code;

	@Expose(serialize = true, deserialize = true)
	@Column(name = "section_code")
	private String sectionCode;

	@Expose(serialize = true, deserialize = true)
	@Column(name = "campus")
	private String campus;

	public Course() {
	}

	public Course(String name, String code, String sectionCode, String campus) {
		this.name = name;
		this.code = code;
		this.sectionCode = sectionCode;
		this.campus = campus;
	}

}
