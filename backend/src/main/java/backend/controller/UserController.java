package backend.controller;

import java.util.List;

import backend.dto.UserDto;
import backend.form.UserForm.*;
import org.springframework.http.ResponseEntity;


public interface UserController {

    ResponseEntity<UserDto> register(UserRegisterForm input);

    ResponseEntity<UserDto> login(UserLoginForm input);

    ResponseEntity<Long> logout(Long id);

    ResponseEntity<List<UserDto>> getAllUsers();

    ResponseEntity<UserDto> getUserById(Long id);

}
