package com.controlHorari.Service;

import com.controlHorari.Entity.User;
import com.controlHorari.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    // CONSTRUCTOR =====================================================================================================
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveOrUpdateUser(User user) {
        Optional<User> existingUserOpt = userRepository.findByEmail(user.getEmail());

        try {
            if (existingUserOpt.isPresent()) {
                User existingUser = existingUserOpt.get();
                existingUser.setFullName(user.getFullName());
                existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
                return userRepository.save(existingUser);
            } else {
                return userRepository.save(user);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to save User: " + e.getMessage());
        }
    }


    public List<User> getAllUsers() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch all Users: " + e.getMessage());
        }
    }


    public Optional<User> getUserByUserId(Long id) {
        try {
            return userRepository.findById(id);
        } catch (Exception e) {
            // Handle exception or log the error
            throw new RuntimeException("Failed to fetch User by ID: " + e.getMessage());
        }
    }

    public boolean deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
            return true; // Deletion successful
        } catch (Exception e) {
            // Handle exception or log the error
            throw new RuntimeException("Failed to delete User: " + e.getMessage());
        }
    }
}