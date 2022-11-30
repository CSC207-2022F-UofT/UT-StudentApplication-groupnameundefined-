package backend.service;

import java.util.List;

import backend.form.UserForm.*;
import backend.model.User;

public interface UserService {

    User createUser(User user);

    User registerUser(RegisterUserForm input);

    User loginUser(LoginUserForm input);

    User logoutUser(Long id);

    List<User> getAllUsers();

    User getUserById(Long id);

    User getUserByEmail(String email);

    boolean existName(String name);

    boolean existEmail(String email);

    boolean existPhone(String phone);

}
