package com.project.banking_api.src.services.usecases.bankAccount;

import com.project.banking_api.src.entities.BankAccount;
import com.project.banking_api.src.exceptions.InvalidBalanceException;
import com.project.banking_api.src.exceptions.NotFoundException;
import com.project.banking_api.src.services.repositories.BankAccountRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class AddBalanceService {

    private BankAccountRepository bankAccountRepo;

    public AddBalanceService(BankAccountRepository bankAccountRepo) {
        this.bankAccountRepo = bankAccountRepo;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public BankAccount addBalance(UUID accountID, BigDecimal balanceToBeAdded){

        BankAccount bankAccount = bankAccountRepo.findByID(accountID);

        if(bankAccount == null){
            throw new NotFoundException("Error, Bank account not found.");
        }

        if(balanceToBeAdded.compareTo(BigDecimal.ZERO) <= 0){
            throw new InvalidBalanceException("Error, Balance must be greater than zero.");
        }

        return bankAccountRepo.addBalance(bankAccount.getId(), balanceToBeAdded);
    }
}
