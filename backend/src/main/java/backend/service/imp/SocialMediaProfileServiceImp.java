package backend.service.imp;

import backend.exception.exceptions.EntityNotFoundException;
import backend.form.SocialMediaProfileForm.*;
import backend.model.SocialMediaProfile;
import backend.model.StudentProfile;
import backend.repository.SectionRepository;
import backend.repository.SocialMediaProfileRepository;
import backend.service.SocialMediaProfileService;
import backend.service.StudentProfileService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SocialMediaProfileServiceImp implements SocialMediaProfileService {

	private final Logger logger;

	private final SocialMediaProfileRepository socialMediaProfileRepository;

	private final StudentProfileService studentProfileService;

	@Autowired
	public SocialMediaProfileServiceImp(
			Logger logger,
			SocialMediaProfileRepository socialMediaProfileRepository,
			StudentProfileService studentProfileService
	) {
		this.logger = logger;
		this.socialMediaProfileRepository = socialMediaProfileRepository;
		this.studentProfileService = studentProfileService;
	}

	@Override
	public List<SocialMediaProfile> getAllSocialMediaProfiles() {
		return socialMediaProfileRepository.findAll();
	}

	@Override
	public SocialMediaProfile getSocialMediaProfileById(Long id) {
		Optional<SocialMediaProfile> socialMediaProfile = socialMediaProfileRepository.findById(id);
		if (socialMediaProfile.isPresent()) {
			return socialMediaProfile.get();
		}

		throw new EntityNotFoundException(
				String.format("Unable to find a social media profile with the given id %s.", id),
				SocialMediaProfile.class
		);
	}

	@Override
	public SocialMediaProfile createSocialMediaProfile(CreateSocialMediaProfileForm input) {
		StudentProfile studentProfile = studentProfileService.getStudentProfileById(input.getStudentProfileId());
		SocialMediaProfile socialMediaProfile = new SocialMediaProfile(
				input.getInstagramId(),
				input.getFacebookId()
		);

		socialMediaProfile.setStudentProfile(studentProfile);
		socialMediaProfileRepository.save(socialMediaProfile);

		return socialMediaProfile;
	}
}
