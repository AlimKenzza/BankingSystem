package com.aitu.project.onlinebankingsystem.controller;


import com.aitu.project.onlinebankingsystem.exception.UserNotFoundException;
import com.aitu.project.onlinebankingsystem.model.User;
import com.aitu.project.onlinebankingsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/userRest")
public class UserRestController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Integer id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            return ResponseEntity.ok().body(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public User saveUser(@Validated @RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/user/{id}")
    public User updateNote(@PathVariable(value = "id") Integer id,
                                      @Valid @RequestBody User userDet) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        user.setUsername(user.getUsername());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEmail(user.getEmail());
        user.setPhone(user.getPhone());

        User updatedUser = userRepository.save(user);
        return updatedUser;
    }
    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity deleteUser(@PathVariable(value = "id") Integer id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }
}
