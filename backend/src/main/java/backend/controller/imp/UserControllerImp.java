package backend.controller.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import backend.form.UserForm.*;
import backend.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.controller.UserController;
import backend.model.User;
import backend.repository.UserRepository;
import backend.service.UserService;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserControllerImp implements UserController {

    @Autowired
    UserService userService;

    @Override
    @GetMapping("/greeting")
    public ResponseEntity<String> getUserGreeting() {
        return new ResponseEntity<>("User: Greetings!", HttpStatus.OK);
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        Optional<User> user = userService.getUserById(id);

        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            User _user = userService.createUser(user);

            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody @Valid UserRegisterForm userRegisterInput) {
        try {
            return new ResponseEntity<User>(userService.registerUser(userRegisterInput), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody UserLoginForm userLoginInput) {
        try {
            return new ResponseEntity<User>(userService.loginUser(userLoginInput), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
