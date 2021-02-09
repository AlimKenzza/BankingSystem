package com.aitu.project.onlinebankingsystem.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class HalykBank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hcardId;
    private int balance;

    public HalykBank() {
    }
    @OneToMany(mappedBy = "halykBank", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HalykTransactionInfo> halykTransactionInfos = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public int getHcardId() {
        return hcardId;
    }

    public void setHcardId(int hcardId) {
        this.hcardId = hcardId;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public List<HalykTransactionInfo> getHalykTransactionInfos() {
        return halykTransactionInfos;
    }

    public void setHalykTransactionInfos(List<HalykTransactionInfo> halykTransactionInfos) {
        this.halykTransactionInfos = halykTransactionInfos;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
