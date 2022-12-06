package backend.model;

import com.google.gson.annotations.Expose;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "section")
public class Section {

	@Setter(AccessLevel.NONE)
	@Expose(serialize = true, deserialize = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Expose(serialize = true, deserialize = true)
	@ManyToOne
	@JoinColumn(name = "course_id", referencedColumnName = "id")
	private Course course;

	@Expose(serialize = true, deserialize = true)
	@Column(name = "name")
	private String name;

	public Section() {
	}

	public Section(String name) {
		this.name = name;
	}

}
