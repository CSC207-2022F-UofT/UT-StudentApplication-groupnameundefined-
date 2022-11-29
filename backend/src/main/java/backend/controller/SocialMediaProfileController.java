package backend.controller;

import backend.dto.SocialMediaProfileDto;
import backend.model.SocialMediaProfile;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SocialMediaProfileController {

    ResponseEntity<List<SocialMediaProfile>> getAllSocialMediaProfiles();

    ResponseEntity<SocialMediaProfileDto> getSocialMediaProfileById(Long id);

}
