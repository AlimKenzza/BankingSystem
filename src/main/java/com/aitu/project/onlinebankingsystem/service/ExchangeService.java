package com.aitu.project.onlinebankingsystem.service;

import com.aitu.project.onlinebankingsystem.model.CustomerAcc;
import com.aitu.project.onlinebankingsystem.model.HalykBank;
import com.aitu.project.onlinebankingsystem.model.HalykTransactionInfo;
import com.aitu.project.onlinebankingsystem.model.TransactionInfo;

import java.util.List;

public interface ExchangeService {
    List<TransactionInfo> findTransactionList(String username);
    List<HalykTransactionInfo> findHalykTransactionList(String username);
    void betweenBanksTransfer(String from, String to, double amount, CustomerAcc customerAcc, HalykBank halykBank) throws Exception;

    void saveCustomerDepositTransaction(TransactionInfo transactionInfo);

    void saveHalykDepositTransaction(HalykTransactionInfo halykTransactionInfo);

    void saveCustomerWithdrawTransaction(TransactionInfo transactionInfo);
    void saveHalykWithdrawTransaction(HalykTransactionInfo halykTransactionInfo);
}
