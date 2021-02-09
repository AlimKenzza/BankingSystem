package com.aitu.project.onlinebankingsystem.service;

import com.aitu.project.onlinebankingsystem.model.CustomerAcc;
import com.aitu.project.onlinebankingsystem.model.TransactionInfo;

import java.security.Principal;
import java.util.List;

public interface CustomerAccService {
    List<CustomerAcc> getAllAccounts();
    CustomerAcc findCustomer(String username);
    void deposit(String bank, double amount, Principal principal);
    void withdraw(String bank, double amount, Principal principal);
}
