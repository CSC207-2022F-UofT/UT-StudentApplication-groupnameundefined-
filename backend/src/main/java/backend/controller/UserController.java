package backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import backend.model.User;

public interface UserController {

    ResponseEntity<String> getUserGreeting();

    ResponseEntity<List<User>> getAllUsers();

    ResponseEntity<User> getUserById(Long id);

    ResponseEntity<User> createUser(User user);
}
