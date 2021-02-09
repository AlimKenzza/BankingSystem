package com.aitu.project.onlinebankingsystem.service;

import com.aitu.project.onlinebankingsystem.model.HalykTransactionInfo;
import com.aitu.project.onlinebankingsystem.model.TransactionInfo;
import org.springframework.stereotype.Service;

import java.util.List;



public interface TransactionInfoService {
    List<TransactionInfo> getAllTransactionInfos();
    List<TransactionInfo> findTransactionList(String username);
    List<HalykTransactionInfo> findHalykTransactionList(String username);
    void saveDepositTransactionInfo(TransactionInfo transactionInfo);
    void saveHalykDepositInfo(HalykTransactionInfo halykTransactionInfo);
}
