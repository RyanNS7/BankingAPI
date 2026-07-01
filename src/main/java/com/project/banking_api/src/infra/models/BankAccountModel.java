package com.project.banking_api.src.infra.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "bankAccounts")
public class BankAccountModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private BigDecimal balance;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    @OneToMany(mappedBy = "accountSent")
    private List<TransferModel> transfersSent;

    @OneToMany(mappedBy = "accountReceived")
    private List<TransferModel> transfersReceived;

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

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public List<TransferModel> getTransfersSent() {
        return transfersSent;
    }

    public void setTransfersSent(List<TransferModel> transfersSent) {
        this.transfersSent = transfersSent;
    }

    public List<TransferModel> getTransfersReceived() {
        return transfersReceived;
    }

    public void setTransfersReceived(List<TransferModel> transfersReceived) {
        this.transfersReceived = transfersReceived;
    }
}
