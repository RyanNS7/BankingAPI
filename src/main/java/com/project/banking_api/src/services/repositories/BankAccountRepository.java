package com.project.banking_api.src.services.repositories;

import com.project.banking_api.src.entities.BankAccount;
import com.project.banking_api.src.entities.Transfer;
import com.project.banking_api.src.entities.User;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.UUID;

@Repository
public interface BankAccountRepository {

    BankAccount create(User user);
    BankAccount addBalance(UUID bankAccount, BigDecimal balance);
    BankAccount subtractBalance(UUID bankAccount, BigDecimal balance);
    BankAccount findByUserID(UUID userID);
    BankAccount findByID(UUID accountID);

}
