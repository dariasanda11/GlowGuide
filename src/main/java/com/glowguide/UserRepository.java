package com.glowguide;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // This allows us to check if an email is already registered
    Optional<User> findByEmail(String email);
}