package com.aitu.project.onlinebankingsystem.service;

import com.aitu.project.onlinebankingsystem.model.CustomerAcc;
import com.aitu.project.onlinebankingsystem.model.TransactionInfo;
import com.aitu.project.onlinebankingsystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TransferServiceImpl implements TransferService{
    @Autowired
    UserService userService;
    @Override
    public List<TransactionInfo> findTransactionInfo(String username) {
        User user = userService.findByUsername(username);
        List<TransactionInfo> transactionInfoList = user.getCustomerAcc().getTransactionInfos();
        return transactionInfoList;
    }

    @Override
    public void transferBetweenCards(String from, String to, int amount, CustomerAcc customerAcc) throws Exception {

    }
}
