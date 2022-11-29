package backend.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "HabitVisibility")
public class HabitVisibility {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "habit_id", referencedColumnName = "id", nullable = false)
    private Habit habit;

    @Column(name = "mbti", columnDefinition = "boolean default false")
    private Boolean mbti;

    @Column(name = "talkative", columnDefinition = "boolean default false")
    private Boolean talkative;

    @Column(name = "collaborate", columnDefinition = "boolean default false")
    private Boolean collaborate;

    public HabitVisibility() {
    }
}
