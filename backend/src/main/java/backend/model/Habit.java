package backend.model;

import lombok.AccessLevel;
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
	@Setter(AccessLevel.NONE)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "studentprofile_id", referencedColumnName = "id", nullable = false)
	private StudentProfile studentProfile;


	// The user's self reported talkativeness(extrovert of introvert), rating 1~5
	@Column(name = "talkative", columnDefinition = "integer default 5")
	private Integer talkative;

	// The user's willingness to collaborate with others, rating 1~5
	@Column(name = "collaborative", columnDefinition = "integer default 5")
	private Integer collaborative;

	public Habit() {
	}

	public Habit(int talkative, int collaborative) {
		this.talkative = talkative;
		this.collaborative = collaborative;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (!(o instanceof Habit habit)) {
			return false;
		}

		return (Objects.equals(this.collaborative, habit.collaborative)) && (Objects.equals(
				this.talkative, habit.talkative));
	}

}
