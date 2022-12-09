package backend.service;

import backend.form.SocialMediaProfileForm.*;
import backend.model.SocialMediaProfile;

import java.util.List;


public interface SocialMediaProfileService {

	List<SocialMediaProfile> getAllSocialMediaProfiles();

	SocialMediaProfile getSocialMediaProfileById(Long id);

	SocialMediaProfile createSocialMediaProfile(CreateSocialMediaProfileForm input);
}
