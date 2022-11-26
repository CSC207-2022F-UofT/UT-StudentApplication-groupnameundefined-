package backend.service;

import java.util.List;
import java.util.Optional;

import backend.form.UserForm;
import backend.model.User;

public interface UserService {

    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    User createUser(User user);

//    void saveUser(User user);

    boolean existName(String name);

//    boolean nameIsValid(String name);

//    boolean passwordIsValid(String password);
//
//    boolean repeatPasswordMatch(String password, String repeatPassword);

    public User registerUser(UserForm.UserRegisterForm userRegisterInput) throws Exception;

}
