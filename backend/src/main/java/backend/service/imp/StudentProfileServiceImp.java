package backend.service.imp;

import java.io.File;
import java.util.*;

import backend.model.*;
import backend.repository.SectionBlockRepository;
import backend.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.form.StudentProfileForm.CreateStudentProfileForm;
import backend.repository.StudentProfileRepository;
import backend.repository.UserRepository;
import backend.service.StudentProfileService;
import org.springframework.web.multipart.MultipartFile;;import javax.persistence.EntityNotFoundException;

@Service
public class StudentProfileServiceImp implements StudentProfileService {

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SectionBlockRepository sectionBlockRepository;

    @Autowired
    private TimetableService timetableService;

    @Override
    public StudentProfile createStudentProfile(CreateStudentProfileForm input) {
        Optional<User> _user = userRepository.findById(input.getUserId());
        StudentProfile _studentProfile = new StudentProfile(input.getProgram(), input.getCollege(), input.getEnrolmentYear());

        _user.ifPresent(_studentProfile::setUser);

        return studentProfileRepository.save(_studentProfile);
    }

    @Override
    public StudentProfile getStudentProfile(Long id) {
        Optional<StudentProfile> _studentProfile = studentProfileRepository.findById(id);

        if (_studentProfile.isPresent()) {
            return _studentProfile.get();
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public List<StudentProfile> getAllStudentProfiles() {
        return new ArrayList<>(studentProfileRepository.findAll());
    }

    // @Override
    // public StudentProfile updateStudentProfile(Long id) {
    // return studentProfile;
    // }

    // @Override
    // public void deleteStudentProfile(Long id) {

    // }

}
