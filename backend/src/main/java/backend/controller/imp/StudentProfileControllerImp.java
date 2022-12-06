package backend.controller.imp;

import java.io.File;
import java.util.*;

import javax.validation.Valid;

import backend.dto.StudentProfileDto;
import backend.mappers.StudentProfileMapper;
import backend.model.Habit;
import backend.repository.StudentProfileRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import backend.controller.StudentProfileController;
import backend.form.StudentProfileForm.*;
import backend.model.StudentProfile;
import backend.service.StudentProfileService;

@CrossOrigin
@RestController
@RequestMapping("/api/student-profile")
public class StudentProfileControllerImp implements StudentProfileController {

	private final Logger logger;

	private final StudentProfileService studentProfileService;
	private final StudentProfileMapper studentProfileMapper;

	@Autowired
	private StudentProfileRepository studentProfileRepository;

	@Autowired
	public StudentProfileControllerImp(
			Logger logger, StudentProfileService studentProfileService, StudentProfileMapper studentProfileMapper
	) {
		this.logger = logger;
		this.studentProfileService = studentProfileService;
		this.studentProfileMapper = studentProfileMapper;
	}

	@Override
	@PostMapping("/create")
	public ResponseEntity<StudentProfileDto> createStudentProfile(@RequestBody @Valid CreateStudentProfileForm input) {
		StudentProfile studentProfile = studentProfileService.createStudentProfile(input);
		StudentProfileDto studentProfileDto = studentProfileMapper.toDto(studentProfile);

		return new ResponseEntity<>(studentProfileDto, HttpStatus.OK);
	}

	@Override
	@GetMapping("/")
	public ResponseEntity<List<StudentProfileDto>> getAllStudentProfiles() {
		List<StudentProfile> studentProfiles = studentProfileService.getAllStudentProfiles();
		List<StudentProfileDto> studentProfileDtos = studentProfileMapper.toDtoList(studentProfiles);

		return new ResponseEntity<>(studentProfileDtos, HttpStatus.OK);
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<StudentProfileDto> getStudentProfileById(@PathVariable Long id) {
		StudentProfile studentProfile = studentProfileService.getStudentProfileById(id);
		StudentProfileDto studentProfileDto = studentProfileMapper.toDto(studentProfile);

		return new ResponseEntity<>(studentProfileDto, HttpStatus.OK);
	}

	@Override
	@GetMapping("/match-habit/{id}")
	public ResponseEntity<List<StudentProfileDto>> matchStudentProfileByHabit(@PathVariable Long id) {
		List<StudentProfile> studentProfiles = studentProfileService.matchStudentProfileByHabit(id);
		List<StudentProfileDto> studentProfileDtos = studentProfileMapper.toDtoList(studentProfiles);

		return new ResponseEntity<>(studentProfileDtos, HttpStatus.OK);
	}

	@Override
	@GetMapping("/match-courses/{id}")
	public ResponseEntity<List<StudentProfileDto>> matchStudentProfileByCourses(@PathVariable Long id) {
		List<StudentProfile> studentProfiles = studentProfileService.matchStudentProfileByCourses(id);
		List<StudentProfileDto> studentProfileDtos = studentProfileMapper.toDtoList(studentProfiles);

		return new ResponseEntity<>(studentProfileDtos, HttpStatus.OK);
	}
}
