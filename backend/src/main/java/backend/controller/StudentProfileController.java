package backend.controller;

import backend.dto.StudentProfileDto;
import org.springframework.http.ResponseEntity;

import backend.form.StudentProfileForm.*;
import backend.model.StudentProfile;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public interface StudentProfileController {

	ResponseEntity<StudentProfileDto> createStudentProfile(CreateStudentProfileForm input);

	ResponseEntity<List<StudentProfileDto>> getAllStudentProfiles();

	ResponseEntity<StudentProfileDto> getStudentProfileById(Long id);

	ResponseEntity<List<StudentProfileDto>> matchStudentProfiles(MatchStudentProfileForm input);

}
