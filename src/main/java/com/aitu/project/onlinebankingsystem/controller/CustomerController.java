package com.aitu.project.onlinebankingsystem.controller;


import com.aitu.project.onlinebankingsystem.model.CustomerAcc;
import com.aitu.project.onlinebankingsystem.model.HalykBank;
import com.aitu.project.onlinebankingsystem.model.User;
import com.aitu.project.onlinebankingsystem.repository.CustomerRepository;
import com.aitu.project.onlinebankingsystem.repository.UserRepository;
import com.aitu.project.onlinebankingsystem.service.CustomerAccService;
import com.aitu.project.onlinebankingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class CustomerController {
    @Autowired
    private CustomerAccService customerAccService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @GetMapping("/customer")
    public String viewHomePage(Model model,Principal principal) {
//        CustomerAcc customerAcc = customerAccService.findCustomer(principal.getName());
        User user = userService.findByUsername(principal.getName());
        CustomerAcc customerAcc = user.getCustomerAcc();
        model.addAttribute("Customer", customerAcc);
        return "userPage";
    }

    @GetMapping("/halyk")
    public String viewHome(Model model,Principal principal) {
//        CustomerAcc customerAcc = customerAccService.findCustomer(principal.getName());
        User user = userService.findByUsername(principal.getName());
        HalykBank halykBank = user.getHalykBank();
        model.addAttribute("HalykBank", halykBank);
        return "halykbank";
    }
    @RequestMapping(value = "/deposit", method = RequestMethod.GET)
    public String deposit(Model model) {
        model.addAttribute("amount", "");

        return "deposit";
    }
    @RequestMapping(value = "/deposit", method = RequestMethod.POST)
    public String depositPost(@ModelAttribute("amount") String amount, @ModelAttribute("bank") String bank, Principal principal) {
        customerAccService.deposit(bank, Double.parseDouble(amount), principal);
        if(bank.equalsIgnoreCase("Kaspi")) {
            return "redirect:/info";
        }
        return "redirect:/halykInfo";
    }

    @RequestMapping(value = "/withdraw", method = RequestMethod.GET)
    public String withdraw(Model model) {
        model.addAttribute("amount", "");
        return "withdraw";
    }
    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    public String withdrawPOST(@ModelAttribute("amount") String amount, @ModelAttribute("bank") String bank, Principal principal) {
        customerAccService.withdraw(bank, Double.parseDouble(amount), principal);
        if(bank.equalsIgnoreCase("Kaspi")) {
            return "redirect:/info";
        }
        return "redirect:/halykInfo";

    }
    @RequestMapping(value = "/withdrawCom", method = RequestMethod.POST)
    public String withdrawComPOST(@ModelAttribute("amount") String amount, @ModelAttribute("bank") String bank, Principal principal) {
        customerAccService.withdraw(bank, Double.parseDouble(amount), principal);
        if(bank.equalsIgnoreCase("Kaspi")) {
            return "redirect:/info";
        }
        return "redirect:/halykInfo";

    }
    @RequestMapping(value = "/withdrawCom", method = RequestMethod.GET)
    public String withdrawCom(Model model) {
        model.addAttribute("amount", "");
        return "payment";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/process_register")
    public String processRegister(HttpServletRequest request, User user, CustomerAcc customerAcc) {
        String username = request.getParameter("username");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setUsername(username);
        user.setPassword(encodedPassword);
        customerAcc.setCardId(user.getId());
        user.setEnabled(true);
        userRepository.save(user);
        customerAcc.setUser(user);
        customerAcc.setBalance(0);
        customerRepository.save(customerAcc);
        return "redirect:/login";
    }
}
