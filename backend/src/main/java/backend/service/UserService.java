package backend.service;

import java.util.List;
import java.util.Optional;

import backend.form.AptRequestForm.*;
import backend.form.FriendRequestForm;
import backend.form.FriendRequestForm.*;
import backend.form.UserForm.*;
import backend.model.AptRequest;
import backend.model.FriendRequest;
import backend.model.User;

public interface UserService {

	User registerUser(RegisterForm input);

	Optional<User> userAuthenticate(String email, String password);

	User loginUser(LoginForm input);

	Long logoutUser(Long id);

	List<User> getAllUsers();

	User getUserById(Long id);

	User getUserByEmail(String email);

	List<User> getFriendsByUserId(Long id);

}
