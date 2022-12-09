package backend.controller;

import backend.dto.SocialMediaProfileDto;
import backend.form.SocialMediaProfileForm.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/social-media-profile")
public interface SocialMediaProfileController {

	@GetMapping("/")
	ResponseEntity<List<SocialMediaProfileDto>> getAllSocialMediaProfiles();

	@GetMapping("/{id}")
	ResponseEntity<SocialMediaProfileDto> getSocialMediaProfileById(@RequestParam Long id);

	@PostMapping("/create")
	ResponseEntity<SocialMediaProfileDto> createSocialMediaProfile(@RequestBody CreateSocialMediaProfileForm input);

}
