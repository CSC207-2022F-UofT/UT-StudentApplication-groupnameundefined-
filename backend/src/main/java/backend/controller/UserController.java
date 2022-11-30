package backend.controller;

import java.util.List;

import backend.dto.UserDto;
import backend.form.UserForm.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;


public interface UserController {

	ResponseEntity<UserDto> registerUser(@RequestBody @Valid RegisterForm input);

	ResponseEntity<UserDto> loginUser(@RequestBody LoginForm input);

	ResponseEntity<Long> logoutUser(@PathVariable("id") Long id);

	ResponseEntity<List<UserDto>> getAllUsers();

	ResponseEntity<UserDto> getUserById(Long id);

}
