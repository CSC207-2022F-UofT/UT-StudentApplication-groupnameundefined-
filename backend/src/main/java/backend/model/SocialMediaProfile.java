package backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import backend.model.StudentProfile;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "social_media_profile")
public class SocialMediaProfile {

	@Setter(AccessLevel.NONE)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "instagram_id")
	private String instagramId;

	@Column(name = "facebook_id")
	private String facebookId;

	@OneToOne
	@JoinColumn(name = "studentprofile_id", referencedColumnName = "id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private StudentProfile studentProfile;

	public SocialMediaProfile() {
	}

}
