package com.aitu.project.onlinebankingsystem.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "receiver")
public class Receiver {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int receiverId;
    private String receiverName;
    private int accountNo;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
