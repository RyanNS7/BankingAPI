package com.project.banking_api.src.entities;

import java.math.BigDecimal;
import java.util.UUID;

public class BankAccount {

    private UUID id;
    private BigDecimal balance;
    private UUID userID;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }
}
