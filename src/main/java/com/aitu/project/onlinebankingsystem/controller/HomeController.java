package com.aitu.project.onlinebankingsystem.controller;


import com.aitu.project.onlinebankingsystem.model.Role;
import com.aitu.project.onlinebankingsystem.model.User;
import com.aitu.project.onlinebankingsystem.repository.UserRepository;
import com.aitu.project.onlinebankingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
//    @RequestMapping(value = "/signup", method = RequestMethod.POST)
//    public String signupPost(@ModelAttribute("user") User user, Model model) {
//
//        if(userService.checkUserExists(user.getUsername(), user.getEmail()))  {
//
//            if (userService.checkEmailExists(user.getEmail())) {
//                model.addAttribute("emailExists", true);
//            }
//
//            if (userService.checkUsernameExists(user.getUsername())) {
//                model.addAttribute("usernameExists", true);
//            }
//
//            return "signup";
//        } else {
//            Set<Role> userRoles = new HashSet<>();
//            //userRoles.add(new Role(user, userRepository.findByUsername("ROLE_USER")));
//
//            userService.createUser(user, userRoles);
//
//            return "redirect:/login";
//        }
//    }
}
