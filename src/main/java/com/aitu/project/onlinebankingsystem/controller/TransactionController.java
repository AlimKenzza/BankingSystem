package com.aitu.project.onlinebankingsystem.controller;


import com.aitu.project.onlinebankingsystem.exception.TransactionNotFoundException;
import com.aitu.project.onlinebankingsystem.model.TransactionInfo;
import com.aitu.project.onlinebankingsystem.repository.TransactionInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionInfoRepository transactionInfoRepository;

    @GetMapping("/all")
    public List<TransactionInfo>  getAllTransactions() {
        return transactionInfoRepository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<TransactionInfo> getTransactionById(@PathVariable(value = "id") Integer id){
        Optional<TransactionInfo> transactionInfo = transactionInfoRepository.findById(id);
        if(transactionInfo.isPresent()) {
            return ResponseEntity.ok().body(transactionInfo.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public TransactionInfo saveTransaction(@Validated @RequestBody TransactionInfo transactionInfo) {
        return transactionInfoRepository.save(transactionInfo);
    }

    @PutMapping("/transaction/{id}")
    public TransactionInfo updateNote(@PathVariable(value = "id") Integer id,
                           @Valid @RequestBody TransactionInfo transactionDetails) throws TransactionNotFoundException {
        TransactionInfo transactionInfo = transactionInfoRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));
        transactionInfo.setPostDate(transactionDetails.getPostDate());
        transactionInfo.setDescription(transactionDetails.getDescription());
        transactionInfo.setStatus(transactionDetails.getStatus());
        transactionDetails.setOperationAmount(transactionDetails.getOperationAmount());
        TransactionInfo updatedTransaction = transactionInfoRepository.save(transactionInfo);
        return updatedTransaction;
    }
    @DeleteMapping("/delete-transaction/{id}")
    public ResponseEntity deleteTransaction(@PathVariable(value = "id") Integer id) throws TransactionNotFoundException {
        TransactionInfo transactionInfo = transactionInfoRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));
        transactionInfoRepository.delete(transactionInfo);
        return ResponseEntity.ok().build();

    }

}
