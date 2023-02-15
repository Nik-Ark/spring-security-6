package com.nikki.security5.custom_auth.repositories;

import com.nikki.security5.custom_auth.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Integer> {

    Optional<User> getUserByIdAndAccessKey(int id, String key);
}
