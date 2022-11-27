package backend.controller;

import java.util.List;

import backend.form.UserForm;
import org.springframework.http.ResponseEntity;

import backend.model.User;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserController {

    ResponseEntity<String> getUserGreeting();

    ResponseEntity<List<User>> getAllUsers();

    ResponseEntity<User> getUserById(Long id);

    ResponseEntity<User> createUser(User user);

    ResponseEntity<User> registerUser(UserForm.UserRegisterForm userRegisterInput);

    ResponseEntity<User> loginUser(UserForm.UserLoginForm userLoginInput);
}
