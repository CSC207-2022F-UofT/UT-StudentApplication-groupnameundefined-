package backend.controller.imp;

import backend.controller.SocialMediaProfileController;
import backend.dto.SocialMediaProfileDto;
import backend.form.SocialMediaProfileForm.*;
import backend.mappers.SocialMediaProfileMapper;
import backend.model.SocialMediaProfile;
import backend.service.SocialMediaProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Controller
public class SocialMediaProfileControllerImp implements SocialMediaProfileController {

	private final SocialMediaProfileService socialMediaProfileService;

	private final SocialMediaProfileMapper socialMediaProfileMapper;

	@Autowired
	public SocialMediaProfileControllerImp(
			SocialMediaProfileService socialMediaProfileService,
			SocialMediaProfileMapper socialMediaProfileMapper
	) {
		this.socialMediaProfileService = socialMediaProfileService;
		this.socialMediaProfileMapper = socialMediaProfileMapper;
	}

	@Override
	public ResponseEntity<List<SocialMediaProfileDto>> getAllSocialMediaProfiles() {
		List<SocialMediaProfile> socialMediaProfiles = socialMediaProfileService.getAllSocialMediaProfiles();
		List<SocialMediaProfileDto> socialMediaProfileDtos = socialMediaProfileMapper.toDtoList(socialMediaProfiles);

		return new ResponseEntity<>(socialMediaProfileDtos, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<SocialMediaProfileDto> getSocialMediaProfileById(Long id) {
		SocialMediaProfile socialMediaProfile = socialMediaProfileService.getSocialMediaProfileById(id);
		SocialMediaProfileDto socialMediaProfileDto = socialMediaProfileMapper.toDto(socialMediaProfile);

		return new ResponseEntity<>(socialMediaProfileDto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<SocialMediaProfileDto> createSocialMediaProfile(CreateSocialMediaProfileForm input) {
		SocialMediaProfile socialMediaProfile = socialMediaProfileService.createSocialMediaProfile(input);
		SocialMediaProfileDto socialMediaProfileDto = socialMediaProfileMapper.toDto(socialMediaProfile);

		return new ResponseEntity<>(socialMediaProfileDto, HttpStatus.OK);
	}
}
