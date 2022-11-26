package backend.repository;

import java.util.List;
import java.util.Optional;

import backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);

}
