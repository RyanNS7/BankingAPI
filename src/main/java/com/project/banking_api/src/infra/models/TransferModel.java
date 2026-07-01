package com.project.banking_api.src.infra.models;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import javax.annotation.processing.Generated;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transfers")
public class TransferModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "accountSent_id")
    private BankAccountModel accountSent;

    @ManyToOne
    @JoinColumn(name = "accountReceived_id")
    private BankAccountModel accountReceived;

    private BigDecimal balance;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dateBalanceTransfer = LocalDateTime.now();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BankAccountModel getAccountSent() {
        return accountSent;
    }

    public void setAccountSent(BankAccountModel accountSent) {
        this.accountSent = accountSent;
    }

    public BankAccountModel getAccountReceived() {
        return accountReceived;
    }

    public void setAccountReceived(BankAccountModel accountReceived) {
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
