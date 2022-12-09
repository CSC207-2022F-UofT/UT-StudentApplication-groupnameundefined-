package backend.controller.imp;

import java.util.*;

import backend.dto.StudentProfileDto;
import backend.mappers.StudentProfileMapper;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import backend.controller.StudentProfileController;
import backend.form.StudentProfileForm.*;
import backend.model.StudentProfile;
import backend.service.StudentProfileService;

@Controller
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
	public ResponseEntity<StudentProfileDto> createStudentProfile(CreateStudentProfileForm input) {
		StudentProfile studentProfile = studentProfileService.createStudentProfile(input);
		StudentProfileDto studentProfileDto = studentProfileMapper.toDto(studentProfile);

		return new ResponseEntity<>(studentProfileDto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<StudentProfileDto> loadCourseIcs(
			Long studentProfileId,
			String session,
			MultipartFile file
	) {
		StudentProfile studentProfile = studentProfileService.loadCourseIcs(studentProfileId, session, file);
		StudentProfileDto studentProfileDto = studentProfileMapper.toDto(studentProfile);

		return new ResponseEntity<>(studentProfileDto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<StudentProfileDto>> getAllStudentProfiles() {
		List<StudentProfile> studentProfiles = studentProfileService.getAllStudentProfiles();
		List<StudentProfileDto> studentProfileDtos = studentProfileMapper.toDtoList(studentProfiles);

		return new ResponseEntity<>(studentProfileDtos, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<StudentProfileDto> getStudentProfileById(Long id) {
		StudentProfile studentProfile = studentProfileService.getStudentProfileById(id);
		StudentProfileDto studentProfileDto = studentProfileMapper.toDto(studentProfile);

		return new ResponseEntity<>(studentProfileDto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<StudentProfileDto>> matchStudentProfiles(Long id, String criteria) {
		List<StudentProfile> studentProfiles = studentProfileService.matchStudentProfiles(id, criteria);
		List<StudentProfileDto> studentProfileDtos = studentProfileMapper.toDtoList(studentProfiles);

		return new ResponseEntity<>(studentProfileDtos, HttpStatus.OK);
	}

}
