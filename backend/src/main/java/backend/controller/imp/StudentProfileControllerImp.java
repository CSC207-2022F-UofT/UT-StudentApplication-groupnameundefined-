package backend.controller.imp;

import java.io.File;
import java.util.*;

import javax.validation.Valid;

import backend.dto.StudentProfileDto;
import backend.mappers.StudentProfileMapper;
import org.slf4j.Logger;
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

    private Logger logger;

    private StudentProfileService studentProfileService;
    private StudentProfileMapper studentProfileMapper;

    @Override
    @PostMapping("/")
    public ResponseEntity<StudentProfileDto> createStudentProfile(@RequestBody @Valid CreateStudentProfileForm input) {
        try {
            StudentProfile studentProfile = studentProfileService.createStudentProfile(input);
            StudentProfileDto studentProfileDto = studentProfileMapper.toDto(studentProfile);
            return new ResponseEntity<>(studentProfileDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<List<StudentProfileDto>> getAllStudentProfiles() {
        try {
            List<StudentProfile> studentProfiles = studentProfileService.getAllStudentProfiles();
            List<StudentProfileDto> studentProfileDtos = studentProfileMapper.toDtoList(studentProfiles);

            return new ResponseEntity<>(studentProfileDtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<StudentProfileDto> getStudentProfile(@PathVariable Long id) {
        try {
            Optional<StudentProfile> studentProfile = studentProfileService.getStudentProfileById(id);

            if (studentProfile.isPresent()) {
                StudentProfileDto studentProfileDto = studentProfileMapper.toDto(studentProfile.get());
                return new ResponseEntity<>(studentProfileDto, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
