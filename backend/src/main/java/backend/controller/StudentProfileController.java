package backend.controller;

import backend.dto.StudentProfileDto;
import org.springframework.http.ResponseEntity;

import backend.form.StudentProfileForm.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
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
            @RequestPart MultipartFile file
    );

    @GetMapping("/")
    ResponseEntity<List<StudentProfileDto>> getAllStudentProfiles();

    @GetMapping("/{id}")
    ResponseEntity<StudentProfileDto> getStudentProfileById(@PathVariable Long id);

    @PostMapping("/match")
    ResponseEntity<List<StudentProfileDto>> matchStudentProfiles(@RequestBody MatchStudentProfileForm input);

}
