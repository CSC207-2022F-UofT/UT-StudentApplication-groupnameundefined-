package backend.controller.imp;

import java.io.File;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.controller.StudentProfileController;
import backend.form.StudentProfileForm.*;
import backend.model.StudentProfile;
import backend.service.StudentProfileService;

@CrossOrigin
@RestController
@RequestMapping("/api/student-profile")
public class StudentProfileControllerImp implements StudentProfileController {

    @Autowired
    StudentProfileService studentProfileService;

    @Override
    @PostMapping("/create-student-profile")
    public ResponseEntity<StudentProfile> createStudentProfile(@RequestBody @Valid CreateStudentProfileForm input) {
        try {
            StudentProfile _studentProfile = studentProfileService.createStudentProfile(input);
            return new ResponseEntity<StudentProfile>(_studentProfile, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/get-student-profile/{id}")
    public ResponseEntity<StudentProfile> getStudentProfile(@RequestParam Long id) {
        try {
            StudentProfile studentProfile = studentProfileService.getStudentProfile(id);
            return new ResponseEntity<StudentProfile>(studentProfile, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
