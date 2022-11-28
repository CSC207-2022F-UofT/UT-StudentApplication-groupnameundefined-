package backend.controller.imp;

import java.io.File;
import java.util.*;

import javax.validation.Valid;

import backend.dto.StudentProfileDto;
import backend.mappers.StudentProfileMapper;
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

    @Autowired
    StudentProfileService studentProfileService;

    @Autowired
    StudentProfileMapper studentProfileMapper;

    @Override
    @PostMapping("/")
    public ResponseEntity<StudentProfile> createStudentProfile(@RequestBody @Valid CreateStudentProfileForm input) {
        try {
            StudentProfile _studentProfile = studentProfileService.createStudentProfile(input);
            return new ResponseEntity<>(_studentProfile, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("")
    public ResponseEntity<List<StudentProfileDto>> getAllStudentProfiles() {
        try {
            List<StudentProfile> studentProfiles = studentProfileService.getAllStudentProfiles();
            List<StudentProfileDto> studentProfileDtos = studentProfileMapper.studentProfilesToDtos(studentProfiles);

            return new ResponseEntity<>(studentProfileDtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<StudentProfile> getStudentProfile(@PathVariable Long id) {
        try {
            StudentProfile studentProfile = studentProfileService.getStudentProfile(id);
            return new ResponseEntity<StudentProfile>(studentProfile, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
