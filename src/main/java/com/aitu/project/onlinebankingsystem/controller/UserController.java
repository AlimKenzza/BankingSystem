package com.aitu.project.onlinebankingsystem.controller;


import com.aitu.project.onlinebankingsystem.model.User;
import com.aitu.project.onlinebankingsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping()
    public List<User> getAllUsers() {
        List<User> users = (List<User>) userRepository.findAll();
        return users;
    }

}
