package com.aitu.project.onlinebankingsystem.service;

import com.aitu.project.onlinebankingsystem.model.*;
import com.aitu.project.onlinebankingsystem.repository.CustomerRepository;
import com.aitu.project.onlinebankingsystem.repository.HalykInfoRepository;
import com.aitu.project.onlinebankingsystem.repository.HalykRepository;
import com.aitu.project.onlinebankingsystem.repository.TransactionInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ExchangeServiceImpl implements ExchangeService {
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionInfoRepository transactionInfoRepository;
    @Autowired
    private HalykInfoRepository halykInfoRepository;
    @Autowired
    private HalykRepository halykRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public List<TransactionInfo> findTransactionList(String username) {
        User user = userService.findByUsername(username);
        List<TransactionInfo> transactionInfoList = user.getCustomerAcc().getTransactionInfos();
        return  transactionInfoList;
    }

    @Override
    public List<HalykTransactionInfo> findHalykTransactionList(String username) {
        User user = userService.findByUsername(username);
        List<HalykTransactionInfo> halykTransactionInfos = user.getHalykBank().getHalykTransactionInfos();
        return halykTransactionInfos;
    }
    public void saveCustomerDepositTransaction(TransactionInfo transactionInfo) {
        transactionInfoRepository.save(transactionInfo);
    }

    public void saveHalykDepositTransaction(HalykTransactionInfo halykTransactionInfo) {
        halykInfoRepository.save(halykTransactionInfo);
    }

    public void saveCustomerWithdrawTransaction(TransactionInfo transactionInfo) {
        transactionInfoRepository.save(transactionInfo);
    }

    public void saveHalykWithdrawTransaction(HalykTransactionInfo halykTransactionInfo) {
        halykInfoRepository.save(halykTransactionInfo);
    }
    @Override
    public void betweenBanksTransfer(String from, String to, double amount, CustomerAcc customerAcc, HalykBank halykBank) throws Exception {
        if (from.equalsIgnoreCase("Kaspi") && to.equalsIgnoreCase("Halyk")) {
            customerAcc.setBalance((int) (customerAcc.getBalance() - amount));
            halykBank.setBalance((int) (halykBank.getBalance() + amount));
            customerRepository.save(customerAcc);
            halykRepository.save(halykBank);

            Date date = new Date();
//            (int) Double.parseDouble(String.valueOf(amount))
            TransactionInfo transactionInfo = new TransactionInfo(date, "Between account transfer from "+from+" to "+to, "Finished", (int) amount, customerAcc, customerAcc.getBalance());
            transactionInfoRepository.save(transactionInfo);
        } else if (from.equalsIgnoreCase("Halyk") && to.equalsIgnoreCase("Kaspi")) {
            customerAcc.setBalance((int) (customerAcc.getBalance() + amount));
            halykBank.setBalance((int) (halykBank.getBalance() - amount));
            customerRepository.save(customerAcc);
            halykRepository.save(halykBank);

            Date date = new Date();

            HalykTransactionInfo halykTransactionInfo = new HalykTransactionInfo(date, "Between account transfer from "+from+" to "+to, "Finished", (int) amount, halykBank, halykBank.getBalance());
            halykInfoRepository.save(halykTransactionInfo);
        } else {
            throw new Exception("Invalid Transfer");
        }
    }
}
