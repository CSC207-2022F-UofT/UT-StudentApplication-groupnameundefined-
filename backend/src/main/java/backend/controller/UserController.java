package backend.controller;

import java.util.List;

import backend.dto.UserDto;
import backend.form.UserForm.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public interface UserController {

    @PostMapping("/register")
    ResponseEntity<UserDto> registerUser(@RequestBody @Valid RegisterForm input);

    @PostMapping("/login")
    ResponseEntity<UserDto> loginUser(@RequestBody @Valid LoginForm input);

    @GetMapping("/logout/{id}")
    ResponseEntity<Long> logoutUser(@PathVariable Long id);

    @GetMapping("/")
    ResponseEntity<List<UserDto>> getAllUsers();

    @GetMapping("/{id}")
    ResponseEntity<UserDto> getUserById(@PathVariable Long id);

    @GetMapping("/{id}/friends")
    ResponseEntity<List<UserDto>> getFriendsByUserId(@PathVariable Long id);

}
