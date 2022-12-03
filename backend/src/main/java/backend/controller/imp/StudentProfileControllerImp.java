package backend.controller.imp;

import java.io.File;
import java.util.*;

import javax.validation.Valid;

import backend.dto.StudentProfileDto;
import backend.mappers.StudentProfileMapper;
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
}
