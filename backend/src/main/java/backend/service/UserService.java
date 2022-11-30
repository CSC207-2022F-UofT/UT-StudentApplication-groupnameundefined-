package backend.service;

import java.util.List;
import java.util.Optional;

import backend.form.UserForm.*;
import backend.model.User;

public interface UserService {

	User registerUser(RegisterForm input);

	Optional<User> authenticateUser(String email, String password);

	User loginUser(LoginForm input);

	Long logoutUser(Long id);

	List<User> getAllUsers();

	User getUserById(Long id);

	User getUserByEmail(String email);

}
