package com.aitu.project.onlinebankingsystem.service;


import com.aitu.project.onlinebankingsystem.model.Role;
import com.aitu.project.onlinebankingsystem.model.User;
import com.aitu.project.onlinebankingsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class UserServiceImpl implements UserService {
    @Autowired UserRepository userRepository;
    @Autowired PasswordEncoder passwordEncoder;

    public void changePassword(User user, String newPassword) {
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public boolean checkEmailExists(String email) {
        return false;
    }

    @Override
    public boolean checkUsernameExists(String username) {
        if (null != findByUsername(username)) {
            return true;
        }

        return false;
    }

    @Override
    public boolean checkUserExists(String username, String email) {
        if (checkUsernameExists(username) || checkEmailExists(username)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User createUser(User user, Set<Role> userRoles) {
        return null;
    }
}
