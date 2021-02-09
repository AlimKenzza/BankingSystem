package com.aitu.project.onlinebankingsystem.service;

import com.aitu.project.onlinebankingsystem.model.CustomerAcc;
import com.aitu.project.onlinebankingsystem.model.TransactionInfo;

import java.util.List;

public interface TransferService {
    List<TransactionInfo> findTransactionInfo(String username);
    void transferBetweenCards(String from, String to, int amount, CustomerAcc customerAcc) throws Exception;
}
