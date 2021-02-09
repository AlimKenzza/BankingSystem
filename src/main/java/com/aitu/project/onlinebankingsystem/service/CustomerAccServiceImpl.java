package com.aitu.project.onlinebankingsystem.service;


import com.aitu.project.onlinebankingsystem.model.*;
import com.aitu.project.onlinebankingsystem.repository.CustomerRepository;
import com.aitu.project.onlinebankingsystem.repository.HalykRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.Principal;
import java.util.Date;
import java.util.List;


@Service
public class CustomerAccServiceImpl implements CustomerAccService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionInfoService transactionInfoService;
    @Autowired
    private HalykRepository halykRepository;
    @Override
    public List<CustomerAcc> getAllAccounts() {
        return customerRepository.findAll();
    }


    @Override
    public CustomerAcc findCustomer(String username) {
        User user = userService.findByUsername(username);
        CustomerAcc customerAcc = user.getCustomerAcc();
        return customerAcc;
    }

    @Override
    public void deposit(String bank, double amount, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        if (bank.equalsIgnoreCase("Kaspi")) {
            CustomerAcc customerAcc = user.getCustomerAcc();
            customerAcc.setBalance((int) (customerAcc.getBalance() + amount));
            customerRepository.save(customerAcc);
            Date date = new Date();
            TransactionInfo transactionInfo = new TransactionInfo(date, "Deposit to the Kaspi account", "success", (int) amount, customerAcc, customerAcc.getBalance(), "Kaspi");
            transactionInfoService.saveDepositTransactionInfo(transactionInfo);
        } else if (bank.equalsIgnoreCase("Halyk")) {
            HalykBank halykBank = user.getHalykBank();
            halykBank.setBalance((int) (halykBank.getBalance() + amount));
            halykRepository.save(halykBank);
            Date date = new Date();
            HalykTransactionInfo halykTransactionInfo = new HalykTransactionInfo(date, "Deposit to the Halyk account", "success", "Halyk", (int) amount, halykBank, halykBank.getBalance());
            transactionInfoService.saveHalykDepositInfo(halykTransactionInfo);
        }
    }

    @Override
    public void withdraw(String bank, double amount, Principal principal) {
        if (bank.equalsIgnoreCase("Kaspi")) {
            User user = userService.findByUsername(principal.getName());
            CustomerAcc customerAcc = user.getCustomerAcc();
            customerAcc.setBalance((int) (customerAcc.getBalance() - amount - (amount*(1/100))));
            customerRepository.save(customerAcc);
            Date date = new Date();
            TransactionInfo transactionInfo = new TransactionInfo(date, "Withdraw from account", "success", (int) amount, customerAcc, customerAcc.getBalance(), "Kaspi");
            transactionInfoService.saveDepositTransactionInfo(transactionInfo);
        }
        else if(bank.equalsIgnoreCase(("Halyk"))) {
            User user = userService.findByUsername(principal.getName());
            HalykBank halykBank = user.getHalykBank();
            halykBank.setBalance((int) (halykBank.getBalance() - amount));
            halykRepository.save(halykBank);
            Date date = new Date();
            HalykTransactionInfo halykTransactionInfo = new HalykTransactionInfo(date, "Withdraw from HalykBank account", "success", "Halyk", (int)amount, halykBank, halykBank.getBalance());
            transactionInfoService.saveHalykDepositInfo(halykTransactionInfo);
        }
    }
}