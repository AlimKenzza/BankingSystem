package com.aitu.project.onlinebankingsystem.controller;


import com.aitu.project.onlinebankingsystem.model.*;
import com.aitu.project.onlinebankingsystem.service.TransactionInfoService;
import com.aitu.project.onlinebankingsystem.service.TransactionInfoServiceImpl;
import com.aitu.project.onlinebankingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class TransactionInfoController {
    @Autowired
    private TransactionInfoService transactionInfoService;
    @Autowired
    private UserService userService;

    @GetMapping("/info")
    public String viewHomePage(Model model, Principal principal) {
        List<TransactionInfo> transactionInfoList = transactionInfoService.findTransactionList(principal.getName());
        User user = userService.findByUsername(principal.getName());
        CustomerAcc customerAcc = user.getCustomerAcc();
        model.addAttribute("listTransactions", transactionInfoList);
        model.addAttribute("customerAcc", customerAcc);
        return "main";
    }
    @GetMapping("/halykInfo")
    public String viewHome(Model model, Principal principal) {
        List<HalykTransactionInfo> transactionInfoList = transactionInfoService.findHalykTransactionList(principal.getName());
        User user = userService.findByUsername(principal.getName());
        HalykBank halykBank = user.getHalykBank();
        model.addAttribute("listHalykTransactions", transactionInfoList);
        model.addAttribute("halykbank", halykBank);
        return "mainHalyk";
    }

    @RequestMapping("mainHalyk")
    public String main(){
        return "mainHalyk";
    }
}
