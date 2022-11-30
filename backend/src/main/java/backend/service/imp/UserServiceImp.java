package backend.service.imp;

import java.util.List;
import java.util.Optional;

import backend.exception.exceptions.EntityNotFoundException;
import backend.form.UserForm.*;
import backend.service.UserService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.model.User;
import backend.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {

	private final Logger logger;

	private final UserRepository userRepository;

	@Autowired
	public UserServiceImp(Logger logger, UserRepository userRepository) {
		this.logger = logger;
		this.userRepository = userRepository;
	}

	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User registerUser(RegisterUserForm) throws Exception {
		if (this.existName(userRegisterInput.name)) {
			logger.error("Name Already Exists.");
			throw new Exception("Name Already Exists.");
		} else if (this.existEmail(userRegisterInput.email)) {
			logger.error("Email Already Exists.");
			throw new Exception("Email Already Exists.");
		} else if (this.existPhone(userRegisterInput.phone)) {
			logger.error("Phone Already Exists.");
			throw new Exception("Phone Already Exists.");
			//        } else if (!this.nameIsValid(userRegisterInput.name)) {
			//            logger.error("Invalid Name.");
			//            throw new Exception("Invalid Name.");
			//        } else if (!this.passwordIsValid(userRegisterInput.password)) {
			//            logger.error("Invalid Password.");
			//            throw new Exception("Invalid Password.");
			//        } else if (! this.repeatPasswordMatch(userRegisterInput.password, userRegisterInput.repeatPassword)) {
			//            logger.error("Password and RepeatPassword Don't Match.");
			//            throw new Exception("Password and RepeatPassword Don't Match.");
		} else {
			return this.createUser(new User(userRegisterInput.name, userRegisterInput.email, userRegisterInput.password, userRegisterInput.phone));
		}
	}

	@Override
	public User loginUser(UserForm.UserLoginForm userLoginInput) throws Exception {
		Optional<User> user = this.getUserByEmail(userLoginInput.email);
		if (!user.isPresent()) {
			logger.error("User With This Email Doesn't Exist.");
			throw new Exception("User With This Email Doesn't Exist.");
		} else if (!(user.get().getPassword().equals(userLoginInput.password))) {
			logger.error("Password Is Incorrect.");
			throw new Exception("Password Is Incorrect.");
		} else {
			System.out.println("Login Successfully.");
			User updatedUser = user.get();
			updatedUser.setLogin();
			return userRepository.save(updatedUser);
		}
	}

	@Override
	public User logoutUser(Long id) throws Exception {
		Optional<User> user = this.getUserById(id);
		if (!user.isPresent()) {
			logger.error("User Doesn't Exist.");
			throw new Exception("User Doesn't Exist.");
		} else {
			User updatedUser = user.get();
			updatedUser.setLogout();
			return userRepository.save(updatedUser);
		}
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(Long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return user.get();
		}

		throw new EntityNotFoundException(String.format("Unable to find user with id %d.", id), User.class);
	}

	@Override
	public User getUserByEmail(String email) {
		Optional<User> user = userRepository.findByEmail(email);
		if (user.isPresent()) {
			return user.get();
		}

		throw new EntityNotFoundException(String.format("Unable to find user with email %s.", email), User.class);
	}


	//    @Override
	//    public void saveUser(User user) {
	//        userRepository.save(user);
	//    }

	@Override
	public boolean existName(String name) {
		Optional<User> user = userRepository.findByName(name);
		return user.isPresent();
	}

	@Override
	public boolean existEmail(String email) {
		Optional<User> user = userRepository.findByEmail(email);
		return user.isPresent();
	}

	@Override
	public boolean existPhone(String phone) {
		Optional<User> user = userRepository.findByPhone(phone);
		return user.isPresent();
	}

}
