package com.project.banking_api.src.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transfer {

    private UUID id;
    private UUID accountSent;
    private UUID accountReceived;
    private BigDecimal balance;
    private LocalDateTime dateBalanceTransfer;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getAccountSent() {
        return accountSent;
    }

    public void setAccountSent(UUID accountSent) {
        this.accountSent = accountSent;
    }

    public UUID getAccountReceived() {
        return accountReceived;
    }

    public void setAccountReceived(UUID accountReceived) {
        this.accountReceived = accountReceived;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDateTime getDateBalanceTransfer() {
        return dateBalanceTransfer;
    }

    public void setDateBalanceTransfer(LocalDateTime dateBalanceTransfer) {
        this.dateBalanceTransfer = dateBalanceTransfer;
    }
}
