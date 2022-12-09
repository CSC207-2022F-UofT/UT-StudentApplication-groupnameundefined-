package backend.form;

import java.io.File;
import java.util.List;

import javax.validation.constraints.*;

import backend.model.StudentProfile;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

public class SocialMediaProfileForm {

	@Getter
	public static class CreateSocialMediaProfileForm {

		private final Long studentProfileId;

		@Size(max = 256)
		private final String instagramId;

		@Size(max = 256)
		private final String facebookId;


		public CreateSocialMediaProfileForm(Long studentProfileId, String instagramId, String facebookId) {
			this.studentProfileId = studentProfileId;
			this.instagramId = instagramId;
			this.facebookId = facebookId;
		}

	}

}
