package backend.controller;

import backend.dto.StudentProfileDto;
import org.springframework.http.ResponseEntity;

import backend.form.StudentProfileForm.*;
import backend.model.StudentProfile;

import java.util.List;
import java.util.Set;

public interface StudentProfileController {

    ResponseEntity<StudentProfile> createStudentProfile(CreateStudentProfileForm input);

    ResponseEntity<StudentProfile> getStudentProfile(Long id);

    ResponseEntity<List<StudentProfileDto>> getAllStudentProfiles();

}
