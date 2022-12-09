package backend.controller;

import backend.dto.StudentProfileDto;
import org.springframework.http.ResponseEntity;

import backend.form.StudentProfileForm.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/student-profile")
public interface StudentProfileController {

	@PostMapping("/create")
	ResponseEntity<StudentProfileDto> createStudentProfile(@RequestBody @Valid CreateStudentProfileForm input);

	@PostMapping("/load-course-ics")
	ResponseEntity<StudentProfileDto> loadCourseIcs(
			@RequestParam Long studentProfileId,
			@RequestParam String session,
			@RequestPart MultipartFile file
	);

	@GetMapping("/")
	ResponseEntity<List<StudentProfileDto>> getAllStudentProfiles();

	@GetMapping("/{id}")
	ResponseEntity<StudentProfileDto> getStudentProfileById(@PathVariable Long id);

	@PostMapping("/match/{id}")
	ResponseEntity<List<StudentProfileDto>> matchStudentProfiles(
			@PathVariable Long id,
			@RequestParam @Pattern(regexp = "HABIT|COURSE|BOTH") String criteria
	);

}
