package com.controlHorari.Controller;

import com.controlHorari.Entity.User;
import com.controlHorari.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.controlHorari.Constants.Constants.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Create a new user
    @PostMapping("/upsert")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User savedUser = userService.saveOrUpdateUser(user);
        return ResponseEntity.ok(savedUser);
    }

    // Get all users
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Get a user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> userOptional = userService.getUserByUserId(id);
        return userOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a user
//    @PutMapping(path = "/user/{userId}")
//    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
//        Optional<User> updateduserOptional = userService.updateUser(id, user);
//        return updateduserOptional.map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }

    //Delte a user
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        boolean deletionStatus = userService.deleteUser(id);
        if (deletionStatus) {
            return ResponseEntity.ok("user with ID " + id + " has been deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete user with ID " + id);
        }
    }
}
