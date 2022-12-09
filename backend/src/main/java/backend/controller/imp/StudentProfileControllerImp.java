package backend.controller.imp;

import java.util.*;

import backend.dto.StudentProfileDto;
import backend.dto.TimetableDto;
import backend.mappers.StudentProfileMapper;

import backend.mappers.TimetableMapper;
import backend.mappers.TimetableMapperImpl;
import backend.model.Timetable;
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

	private final TimetableMapper timetableMapper;

	@Autowired
	public StudentProfileControllerImp(
			Logger logger, StudentProfileService studentProfileService, StudentProfileMapper studentProfileMapper,
			TimetableMapper timetableMapper
	) {
		this.logger = logger;
		this.studentProfileService = studentProfileService;
		this.studentProfileMapper = studentProfileMapper;
		this.timetableMapper = timetableMapper;
	}

	@Override
	public ResponseEntity<StudentProfileDto> createStudentProfile(CreateStudentProfileForm input) {
		StudentProfile studentProfile = studentProfileService.createStudentProfile(input);
		StudentProfileDto studentProfileDto = studentProfileMapper.toDto(studentProfile);

		return new ResponseEntity<>(studentProfileDto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<TimetableDto> loadCourseIcs(
			Long studentProfileId,
			String session,
			MultipartFile file
	) {
		Timetable timetable = studentProfileService.loadCourseIcs(studentProfileId, session, file);
		TimetableDto timetableDto = timetableMapper.toDto(timetable);

		return new ResponseEntity<>(timetableDto, HttpStatus.OK);
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
