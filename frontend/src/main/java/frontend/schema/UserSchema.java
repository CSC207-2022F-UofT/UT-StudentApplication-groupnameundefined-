package frontend.schema;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Getter
@Setter
@Component
public class UserSchema {
	private Long id;
	private String name;
	private String email;
	private String phone;
	private Boolean loginStatus;
	private Timestamp joinedTime;
	private Timestamp lastActiveTime;
	private StudentProfileSchema studentProfile;

	public UserSchema() {

	}
}
