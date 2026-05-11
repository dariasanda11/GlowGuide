package com.glowguide;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // CHANGED: Added 'String name' to the parameters
    public void registerUser(String name, String email, String rawPassword, String role) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email is already registered.");
        }

        User user = new User();
        user.setName(name); // NEW: Save the name to the user object
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRole(role);

        userRepository.save(user);
    }

    // Your loginUser method stays exactly the same!
    public User loginUser(String email, String rawPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid email or password."));

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new RuntimeException("Invalid email or password.");
        }

        return user;
    }
}