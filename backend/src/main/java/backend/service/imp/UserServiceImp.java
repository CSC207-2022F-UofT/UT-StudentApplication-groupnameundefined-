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
    public Optional<User> getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
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

    @Override
    public boolean existEmail(String email){
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent();
    }

    @Override
    public boolean existPhone(String phone){
        Optional<User> user = userRepository.findByPhone(phone);
        return user.isPresent();
    }

//    @Override
//    public boolean repeatPasswordMatch(String password, String repeatPassword) {
//        return password == repeatPassword;
//    }

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
//
//    @Override
//    public boolean passwordIsValid(String password) {
//        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}$");
//        if (password == null){
//            return false;
//        }
//        Matcher matcher = pattern.matcher(password);
//        return matcher.matches();
//    }

    @Override
    public User registerUser(UserForm.UserRegisterForm userRegisterInput) throws Exception {
        if (this.existName(userRegisterInput.name)) {
            logger.error("Name Already Exists.");
            throw new Exception("Name Already Exists.");
        } else if (this.existEmail(userRegisterInput.email)) {
            logger.error("Email Already Exists.");
            throw new Exception("Email Already Exists.");
        } else if (this.existPhone(userRegisterInput.phone)) {
            logger.error("Phone Already Exists.");
            throw new Exception("Phone Already Exists.");
//        } else if (!this.nameIsValid(userRegisterInput.name)) {
//            logger.error("Invalid Name.");
//            throw new Exception("Invalid Name.");
//        } else if (!this.passwordIsValid(userRegisterInput.password)) {
//            logger.error("Invalid Password.");
//            throw new Exception("Invalid Password.");
//        } else if (! this.repeatPasswordMatch(userRegisterInput.password, userRegisterInput.repeatPassword)) {
//            logger.error("Password and RepeatPassword Don't Match.");
//            throw new Exception("Password and RepeatPassword Don't Match.");
        } else {
            return this.createUser(new User(userRegisterInput.name, userRegisterInput.email,
                    userRegisterInput.password, userRegisterInput.phone));
        }
    }

    @Override
    public User loginUser(UserForm.UserLoginForm userLoginInput) throws Exception {
        Optional<User> user = this.getUserByEmail(userLoginInput.email);
        if (! user.isPresent()) {
            logger.error("User With This Email Doesn't Exist.");
            throw new Exception("User With This Email Doesn't Exist.");
        } else if (!(user.get().getPassword().equals(userLoginInput.password))) {
            logger.error("Password Is Incorrect.");
            throw new Exception("Password Is Incorrect.");
        } else {
            System.out.println("Login Successfully.");
            User updatedUser = user.get();
            updatedUser.setLogin();
            return userRepository.save(updatedUser);
        }
    }

    @Override
    public User logoutUser(Long id) throws Exception {
        Optional<User> user = this.getUserById(id);
        if (! user.isPresent()) {
            logger.error("User Doesn't Exist.");
            throw new Exception("User Doesn't Exist.");
        } else {
            User updatedUser = user.get();
            updatedUser.setLogout();
            return userRepository.save(updatedUser);
        }
    }

    //logout: session invalidation, redirect to login.
}
