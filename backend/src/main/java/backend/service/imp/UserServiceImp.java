package backend.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.model.User;
import backend.repository.UserRepository;
import backend.service.UserService;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();

        userRepository.findAll().forEach(users::add);

        return users;
    }

    @Override
    public Optional<User> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    @Override
    public User createUser(User user) {
        User _user = userRepository.save(new User(user.getName(), user.getEmail(), user.getPassword(), user.getPhone()));

        return _user;
    }

    @Override
    public boolean existName(String name){
        Optional<User> user = userRepository.findByName(name);
        return user.isPresent();
    }

    @Override
    public boolean userRegister(String name, String email, String password, String phone, String repeatPassword){
        User user = new User(name, email, password, phone);
        Optional<User> existedUser = userRepository.findByName(name);
        if (!existedUser.isPresent()) {
            if (user.nameIsValid(name) && user.passwordIsValid(password) && password == repeatPassword) {
                User _user = userRepository.save(user);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
