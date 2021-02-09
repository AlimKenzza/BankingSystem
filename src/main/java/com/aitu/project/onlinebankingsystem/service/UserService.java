package com.aitu.project.onlinebankingsystem.service;

import com.aitu.project.onlinebankingsystem.model.Role;
import com.aitu.project.onlinebankingsystem.model.User;

import java.util.Set;

public interface UserService {
    User findByUsername(String username);

    boolean checkUserExists(String username, String email);

    boolean checkUsernameExists(String username);
    boolean checkEmailExists(String email);
    User createUser(User user, Set<Role> userRoles);
}
