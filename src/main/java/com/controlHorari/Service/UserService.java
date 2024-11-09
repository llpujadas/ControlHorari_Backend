package com.controlHorari.Service;

import com.controlHorari.Entity.User;
import com.controlHorari.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            // Handle exception or log the error
            throw new RuntimeException("Failed to save User: " + e.getMessage());
        }
    }

    // Get all Users
    public List<User> fetchAllUsers() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            // Handle exception or log the error
            throw new RuntimeException("Failed to fetch all Users: " + e.getMessage());
        }
    }

    // Get a User by ID
    public Optional<User> getUserByUserId(Long id) {
        try {
            return userRepository.findById(id);
        } catch (Exception e) {
            // Handle exception or log the error
            throw new RuntimeException("Failed to fetch User by ID: " + e.getMessage());
        }
    }

//    public Optional<User> updateUser(Long id, User updatedUser) {
//        try {
//            Optional<User> existingUserOptional = userRepository.findById(id);
//            if (existingUserOptional.isPresent()) {
//                User existingUser = existingUserOptional.get();
//                existingUser.setName(updatedUser.getName());
//                existingUser.setFirstSurname(updatedUser.getFirstSurname());
//                existingUser.setLastSurname(updatedUser.getLastSurname());
//                User savedEntity = userRepository.save(existingUser);
//                return Optional.of(savedEntity);
//            } else {
//                return Optional.empty();
//            }
//        } catch (Exception e) {
//            // Handle exception or log the error
//            throw new RuntimeException("Failed to update User: " + e.getMessage());
//        }
//    }

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