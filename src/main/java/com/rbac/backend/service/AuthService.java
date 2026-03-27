package com.rbac.backend.service;

import com.rbac.backend.dto.RegisterRequest;
import com.rbac.backend.model.User;
import com.rbac.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String registerUser(RegisterRequest request) {
        // 1. Check if user already exists
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return "Error: Username is already taken!";
        }

        // 2. Create new User entity
        User user = new User();
        user.setUsername(request.getUsername());

        // 3. Encrypt the password before saving
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // 4. Assign a default role if none is provided
        user.setRole(request.getRole() != null ? request.getRole() : "ROLE_USER");

        // 5. Save to MySQL
        userRepository.save(user);

        return "User registered successfully!";
    }
}