package com.aitu.project.onlinebankingsystem.service;

import com.aitu.project.onlinebankingsystem.model.HalykTransactionInfo;
import com.aitu.project.onlinebankingsystem.model.TransactionInfo;
import com.aitu.project.onlinebankingsystem.model.User;
import com.aitu.project.onlinebankingsystem.repository.HalykInfoRepository;
import com.aitu.project.onlinebankingsystem.repository.TransactionInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TransactionInfoServiceImpl implements TransactionInfoService {

    @Autowired
    private TransactionInfoRepository transactionInfoRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private HalykInfoRepository halykRepository;
    @Override
    public List<TransactionInfo> getAllTransactionInfos() {
        return transactionInfoRepository.findAll();
    }

    @Override
    public List<TransactionInfo> findTransactionList(String username) {
        User user = userService.findByUsername(username);
        List<TransactionInfo> transactionInfoList = user.getCustomerAcc().getTransactionInfos();
        return transactionInfoList;
    }

    @Override
    public List<HalykTransactionInfo> findHalykTransactionList(String username) {
        User user = userService.findByUsername(username);
        List<HalykTransactionInfo> halykTransactionInfos = user.getHalykBank().getHalykTransactionInfos();
        return halykTransactionInfos;
    }

    @Override
    public void saveHalykDepositInfo(HalykTransactionInfo halykTransactionInfo) {
        halykRepository.save(halykTransactionInfo);
    }

    @Override
    public void saveDepositTransactionInfo(TransactionInfo transactionInfo) {
        transactionInfoRepository.save(transactionInfo);
    }
}
