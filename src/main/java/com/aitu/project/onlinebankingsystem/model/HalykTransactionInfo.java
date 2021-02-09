package com.aitu.project.onlinebankingsystem.model;


import javax.persistence.*;
import java.util.Date;

@Entity
public class HalykTransactionInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Date postDate;
    private String description;
    private String status;
    private String bank;
    private int operationAmount;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "hcardid")
    private HalykBank halykBank;

    private int existingBalance;

    public HalykTransactionInfo() {
    }

    public HalykTransactionInfo(Date postDate, String description, String status, String bank, int operationAmount, HalykBank halykBank, int existingBalance) {
        this.postDate = postDate;
        this.description = description;
        this.status = status;
        this.bank = bank;
        this.operationAmount = operationAmount;
        this.halykBank = halykBank;
        this.existingBalance = existingBalance;
    }

    public HalykTransactionInfo(Date postDate, String description, String status, int operationAmount, HalykBank halykBank, int existingBalance) {
        this.postDate = postDate;
        this.description = description;
        this.status = status;
        this.operationAmount = operationAmount;
        this.halykBank = halykBank;
        this.existingBalance = existingBalance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getOperationAmount() {
        return operationAmount;
    }

    public void setOperationAmount(int operationAmount) {
        this.operationAmount = operationAmount;
    }

    public HalykBank getHalykBank() {
        return halykBank;
    }

    public void setHalykBank(HalykBank halykBank) {
        this.halykBank = halykBank;
    }

    public int getExistingBalance() {
        return existingBalance;
    }

    public void setExistingBalance(int existingBalance) {
        this.existingBalance = existingBalance;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }
}
