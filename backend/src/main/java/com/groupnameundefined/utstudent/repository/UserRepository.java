package com.groupnameundefined.utstudent.repository;

import java.util.List;

import com.groupnameundefined.utstudent.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String name);
}
