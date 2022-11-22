package backend.service;

import java.util.List;
import java.util.Optional;

import backend.model.User;

public interface UserService {

    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    User createUser(User user);

}
