package backend.controller.imp;

import java.util.List;

import backend.dto.UserDto;
import backend.form.UserForm.*;
import backend.mappers.UserMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import backend.controller.UserController;
import backend.model.User;
import backend.service.UserService;
import org.springframework.stereotype.Controller;


@Controller
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
	public ResponseEntity<UserDto> registerUser(RegisterForm input) {
		User user = userService.registerUser(input);
		UserDto userDto = userMapper.toDto(user);

		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserDto> loginUser(LoginForm input) {
		User user = userService.loginUser(input);
		UserDto userDto = userMapper.toDto(user);

		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Long> logoutUser(Long id) {
		return new ResponseEntity<>(userService.logoutUser(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<UserDto>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		List<UserDto> userDtos = userMapper.toDtoList(users);

		return new ResponseEntity<>(userDtos, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserDto> getUserById(Long id) {
		User user = userService.getUserById(id);
		UserDto userDto = userMapper.toDto(user);

		return new ResponseEntity<>(userDto, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<List<UserDto>> getFriendsByUserId(Long id) {

		List<User> friends = userService.getFriendsByUserId(id);
		List<UserDto> userDtos = userMapper.toDtoList(friends);

		return new ResponseEntity<>(userDtos, HttpStatus.OK);
	}


}
