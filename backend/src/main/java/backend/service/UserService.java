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

    /**
     * @param input An input defined by RegisterForm
     * @return A User created with the given input
     */
    User registerUser(RegisterForm input);

    /**
     * @param email    Email of the User account.
     * @param password Password of the User account.
     * @return The User this email corresponds to.
     */
    Optional<User> userAuthenticate(String email, String password);

    /**
     * @param input An input defined by LoginForm
     * @return A User logged in with the given input
     * @throws backend.exception.exceptions.InvalidCredentialsException If the password is false
     */
    User loginUser(LoginForm input);

    /**
     * @param id ID of the User trying to log out.
     * @return id of the User has logged out
     */
    Long logoutUser(Long id);

    /**
     * @return All Users in the AptRequest Table
     */
    List<User> getAllUsers();

    /**
     * @param id ID of the User to find
     * @return The designated User with the given id
     * @throws backend.exception.exceptions.EntityNotFoundException If the User could not be found
     */
    User getUserById(Long id);

    /**
     * @param email email of the User to find
     * @return The designated User with the given email
     * @throws backend.exception.exceptions.EntityNotFoundException If the User could not be found
     */
    User getUserByEmail(String email);

    /**
     * @param id ID of the User
     * @return The designated User's friends with the given ID
     * @throws backend.exception.exceptions.EntityNotFoundException If the User could not be found
     */
    List<User> getFriendsByUserId(Long id);

}
