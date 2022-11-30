package backend.controller.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import backend.dto.UserDto;
import backend.form.UserForm.*;
import backend.mappers.UserMapper;
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
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@RequestBody @Valid RegisterUserForm input) {
		User user = userService.registerUser(input);
		UserDto userDto = userMapper.toDto(user);

		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}

	@Override
	@PostMapping("/login")
	public ResponseEntity<UserDto> loginUser(@RequestBody LoginUserForm input) {
		User user = userService.loginUser(input);
		UserDto userDto = userMapper.toDto(user);

		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}

	@Override
	@GetMapping("/logout/{id}")
	public ResponseEntity<Long> logoutUser(@PathVariable("id") Long id) {
		return new ResponseEntity<>(userService.logoutUser(id), HttpStatus.OK);
	}

	@Override
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		List<UserDto> userDtos = userMapper.toDtoList(users);

		return new ResponseEntity<>(userDtos, HttpStatus.OK);
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) {
		User user = userService.getUserById(id);
		UserDto userDto = userMapper.toDto(user);

		return new ResponseEntity<>(userDto, HttpStatus.OK);

	}

}
