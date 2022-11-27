package backend.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import backend.form.UserForm;
import backend.service.UserService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import backend.model.User;
import backend.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Logger logger;

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();

        userRepository.findAll().forEach(users::add);

        return users;
    }

    @Override
    public Optional<User> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    @Override
    public User createUser(User user) {
        User _user = userRepository.save(new User(user.getName(), user.getEmail(),
                user.getPassword(), user.getPhone()));

        return _user;
    }

//    @Override
//    public void saveUser(User user) {
//        userRepository.save(user);
//    }

    @Override
    public boolean existName(String name){
        Optional<User> user = userRepository.findByName(name);
        return user.isPresent();
    }

//    @Override
//    public boolean nameIsValid(String name) {
//        if (name == null){
//            return false;
//        } else if (name.length() > 20){
//            return false;
//        } else {
//            return true;
//        }
//    }

//    @Override
//    public boolean passwordIsValid(String password) {
//        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}$");
//        if (password == null){
//            return false;
//        }
//        Matcher matcher = pattern.matcher(password);
//        return matcher.matches();
//    }

//    @Override
//    public boolean repeatPasswordMatch(String password, String repeatPassword) {
//        return password == repeatPassword;
//    }

    @Override
    public User registerUser(UserForm.UserRegisterForm userRegisterInput) throws Exception {
        if (this.existName(userRegisterInput.name)) {
            logger.error("Name Already Exists.");
            throw new Exception("Name Already Exists.");
        } else {
            return this.createUser(new User(userRegisterInput.name, userRegisterInput.email,
                    userRegisterInput.password, userRegisterInput.phone));
        }
    }
}
