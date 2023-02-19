package com.nikki.security_6.repositories;

import com.nikki.security_6.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Integer> {

    Optional<User> getUserByIdAndAccessKey(int id, String key);
    Optional<User> findUserByUsername(String username);
}
