package backend.service.imp;

import java.io.File;
import java.util.*;

import backend.exception.exceptions.EntityNotFoundException;
import backend.model.*;
import backend.repository.SectionBlockRepository;
import backend.service.TimetableService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.form.StudentProfileForm.CreateStudentProfileForm;
import backend.repository.StudentProfileRepository;
import backend.repository.UserRepository;
import backend.service.StudentProfileService;
import org.springframework.web.multipart.MultipartFile;;

@Service
public class StudentProfileServiceImp implements StudentProfileService {

	private final Logger logger;
	private final StudentProfileRepository studentProfileRepository;
	private final UserRepository userRepository;
	private final SectionBlockRepository sectionBlockRepository;
	private final TimetableService timetableService;

	@Autowired
	public StudentProfileServiceImp(
			Logger logger,
			StudentProfileRepository studentProfileRepository,
			UserRepository userRepository,
			SectionBlockRepository sectionBlockRepository,
			TimetableService timetableService
	) {
		this.logger = logger;
		this.studentProfileRepository = studentProfileRepository;
		this.userRepository = userRepository;
		this.sectionBlockRepository = sectionBlockRepository;
		this.timetableService = timetableService;
	}

	@Override
	public StudentProfile createStudentProfile(CreateStudentProfileForm input) {
		Optional<User> _user = userRepository.findById(input.getUserId());
		if (_user.isEmpty()) {
			throw new EntityNotFoundException(String.format("Unable to find user with id %d", input.getUserId()), User.class);
		}

		User user = _user.get();
		StudentProfile studentProfile = new StudentProfile(input.getProgram(), input.getCollege(), input.getEnrolmentYear());

		user.setStudentProfile(studentProfile);
		studentProfile.setUser(user);

		return userRepository.save(user).getStudentProfile();
	}

	@Override
	public List<StudentProfile> getAllStudentProfiles() {
		return studentProfileRepository.findAll();
	}

	@Override
	public StudentProfile getStudentProfileById(Long id) {
		Optional<StudentProfile> studentProfile = studentProfileRepository.findById(id);
		if (studentProfile.isPresent()) {
			return studentProfile.get();
		}

		throw new EntityNotFoundException(String.format("Unable to find student profile with id '%d'.", id), StudentProfile.class);
	}

}
