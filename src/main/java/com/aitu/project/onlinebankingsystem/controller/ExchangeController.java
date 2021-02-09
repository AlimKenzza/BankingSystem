package com.aitu.project.onlinebankingsystem.controller;


import com.aitu.project.onlinebankingsystem.model.CustomerAcc;
import com.aitu.project.onlinebankingsystem.model.HalykBank;
import com.aitu.project.onlinebankingsystem.model.User;
import com.aitu.project.onlinebankingsystem.service.ExchangeService;
import com.aitu.project.onlinebankingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class ExchangeController {
    @Autowired
    private UserService userService;
    @Autowired
    private ExchangeService exchangeService;
    @RequestMapping(value = "/between", method = RequestMethod.GET)
    public String betweenAccounts(Model model) {
        model.addAttribute("from", "");
        model.addAttribute("to", "");
        model.addAttribute("amount", "");

        return "exchange";
    }
    @RequestMapping(value = "/betweenAccounts", method = RequestMethod.POST)
    public String betweenAccountsPost(
            @ModelAttribute("from") String from,
            @ModelAttribute("to") String to,
            @ModelAttribute("amount") double amount,
            Principal principal
    ) throws Exception {
        User user = userService.findByUsername(principal.getName());
        CustomerAcc customerAcc = user.getCustomerAcc();
        HalykBank halykBank = user.getHalykBank();
        exchangeService.betweenBanksTransfer(from, to, amount, customerAcc, halykBank);

        return "redirect:/info";
    }
}
