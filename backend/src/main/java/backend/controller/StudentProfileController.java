package backend.controller;

import backend.dto.StudentProfileDto;
import org.springframework.http.ResponseEntity;

import backend.form.StudentProfileForm.*;
import backend.model.StudentProfile;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public interface StudentProfileController {

	ResponseEntity<StudentProfileDto> createStudentProfile(CreateStudentProfileForm input);

	ResponseEntity<Set<StudentProfileDto>> getAllStudentProfiles();

	ResponseEntity<StudentProfileDto> getStudentProfileById(Long id);

	ResponseEntity<LinkedHashSet<StudentProfile>> matchStudentProfileByHabit(Long id);

	ResponseEntity<Set<StudentProfileDto>> matchStudentProfileByCourses(Long id);

}
