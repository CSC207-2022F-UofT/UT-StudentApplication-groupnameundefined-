package backend.controller;

import org.springframework.http.ResponseEntity;

import backend.form.StudentProfileForm.*;
import backend.model.StudentProfile;

public interface StudentProfileController {

    ResponseEntity<StudentProfile> createStudentProfile(CreateStudentProfileForm input);

    ResponseEntity<StudentProfile> getStudentProfile(Long id);

}
