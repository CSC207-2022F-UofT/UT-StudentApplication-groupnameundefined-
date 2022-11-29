package backend.controller.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import backend.dto.UserDto;
import backend.form.UserForm.*;
import backend.service.imp.UserServiceImp;
import lombok.extern.java.Log;
import org.slf4j.Logger;
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

    private final Logger logger;

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserControllerImp(Logger logger, UserService userService, UserMapper userMapper) {
        this.logger = logger;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserForm input) {
        try {
            User user = userService.createUser(input);
            UserDto userDto = userMapper.toDto(user);
            return new ResponseEntity<>(userDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody @Valid RegisterUserForm input) {
        try {
            User user = userService.registerUser(input);
            UserDto userDto = userMapper.toDto(user);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PostMapping("/login")
    public ResponseEntity<UserDto> loginUser(@RequestBody LoginUserForm input) {
        try {
            User user = userService.loginUser(input);
            UserDto userDto = userMapper.toDto(user);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/logout/{id}")
    public ResponseEntity logoutUser(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<User>(userService.logoutUser(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            List<UserDto> userDtos = userMapper.toDtos(users);
            return new ResponseEntity<>(userDtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) {
        try {
            Optional<User> user = userService.getUserById(id);
            if (user.isPresent()) {
                UserDto userDto = userMapper.toDto(user.get());
                return new ResponseEntity<>(userDto, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
