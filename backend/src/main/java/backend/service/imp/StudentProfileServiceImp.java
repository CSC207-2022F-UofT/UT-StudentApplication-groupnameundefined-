package backend.service.imp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.form.StudentProfileForm.CreateStudentProfileForm;
import backend.model.StudentProfile;
import backend.model.User;
import backend.repository.StudentProfileRepository;
import backend.repository.UserRepository;
import backend.service.StudentProfileService;;

@Service
public class StudentProfileServiceImp implements StudentProfileService {

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public StudentProfile createStudentProfile(CreateStudentProfileForm input) {
        Optional<User> _user = userRepository.findById(input.getUserId());
        StudentProfile _studentProfile = input.getStudentProfile();

        _user.ifPresent(user -> {
            _studentProfile.setUser(user);
        });

        StudentProfile studentProfile = studentProfileRepository.save(_studentProfile);

        return studentProfile;
    }

    @Override
    public StudentProfile getStudentProfile(Long id) {
        Optional<StudentProfile> _studentProfile = studentProfileRepository.findById(id);
        StudentProfile studentProfile = _studentProfile.get();

        return studentProfile;
    }

    // @Override
    // public StudentProfile updateStudentProfile(Long id) {
    // return studentProfile;
    // }

    // @Override
    // public void deleteStudentProfile(Long id) {

    // }

}
