package com.baeldung.application.controllers;

import com.baeldung.application.entities.User;
import com.baeldung.application.repositories.UserRepository;
import com.baeldung.ecommerce.exception.ResourceNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }
    
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId)
        throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
          .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + userId));
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/users")
    void addUser(@RequestBody User user) {
        userRepository.save(user);
    }
    
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId,
         @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        user.setEmail(userDetails.getEmail());
        user.setName(userDetails.getName());
        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }
    

    @DeleteMapping("/users/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId)
         throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
       .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
