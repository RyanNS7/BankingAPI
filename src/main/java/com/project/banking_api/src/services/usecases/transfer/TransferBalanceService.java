package com.project.banking_api.src.services.usecases.transfer;

import com.project.banking_api.src.dtos.AccountBalanceDTO;
import com.project.banking_api.src.entities.BankAccount;
import com.project.banking_api.src.entities.Transfer;
import com.project.banking_api.src.exceptions.InsufficientBalanceException;
import com.project.banking_api.src.exceptions.InvalidBalanceException;
import com.project.banking_api.src.exceptions.InvalidTransferException;
import com.project.banking_api.src.services.repositories.BankAccountRepository;
import com.project.banking_api.src.services.repositories.TransferRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class TransferBalanceService {

    private BankAccountRepository bankAccountRepo;
    private TransferRepository transferRepo;

    public TransferBalanceService(BankAccountRepository bankAccountRepo, TransferRepository transferRepo) {
        this.bankAccountRepo = bankAccountRepo;
        this.transferRepo = transferRepo;
    }

    @Transactional
    public Transfer transfer(AccountBalanceDTO accountBalanceDTO, UUID userID){

        BankAccount bankAccount = bankAccountRepo.findByUserID(userID);

        if(accountBalanceDTO.balance().compareTo(BigDecimal.ZERO) <= 0){
            throw new InvalidBalanceException("Error, Balance must be greater than zero.");
        }

        if(bankAccount.getBalance().compareTo(accountBalanceDTO.balance()) < 0){
            throw new InsufficientBalanceException("Insufficient balance for the transfer; additional funds are required.");
        }

        BankAccount accountsReceivable = bankAccountRepo.findByID(accountBalanceDTO.accountReceive());

        if(bankAccount.getId().equals(accountsReceivable.getId())){
            throw new InvalidTransferException("Transfers to the same account are not allowed.");
        }

        bankAccountRepo.addBalance(accountsReceivable.getId(), accountBalanceDTO.balance());
        bankAccountRepo.subtractBalance(bankAccount.getId(), accountBalanceDTO.balance());

        Transfer transfer = new Transfer();
        transfer.setBalance(accountBalanceDTO.balance());
        transfer.setAccountSent(bankAccount.getId());
        transfer.setAccountReceived(accountsReceivable.getId());

        return transferRepo.transferBalance(transfer);

    }
}
