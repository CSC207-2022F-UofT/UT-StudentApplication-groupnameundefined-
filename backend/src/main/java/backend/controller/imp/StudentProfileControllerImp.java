package backend.controller.imp;

import java.io.File;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
