package backend.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.form.StudentProfileForm.CreateStudentProfileForm;
import backend.model.StudentProfile;
import backend.repository.StudentProfileRepository;
import backend.service.StudentProfileService;;

@Service
public class StudentProfileServiceImp implements StudentProfileService {

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Override
    public StudentProfile createStudentProfile(CreateStudentProfileForm input) {
        return studentProfile;
    }

    @Override
    public StudentProfile updateStudentProfile(Long id) {
        return studentProfile;
    }

    @Override
    public void deleteStudentProfile(Long id) {

    }

}
