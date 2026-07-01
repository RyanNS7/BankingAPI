package com.project.banking_api.src.adapters.out.implementations;

import com.project.banking_api.src.entities.BankAccount;
import com.project.banking_api.src.entities.Transfer;
import com.project.banking_api.src.entities.User;
import com.project.banking_api.src.exceptions.NotFoundException;
import com.project.banking_api.src.infra.models.BankAccountModel;
import com.project.banking_api.src.infra.models.TransferModel;
import com.project.banking_api.src.infra.repositories.BankAccountRepositoryDB;
import com.project.banking_api.src.infra.repositories.TransferModelDB;
import com.project.banking_api.src.mappers.BankMapper;
import com.project.banking_api.src.mappers.TransferMapper;
import com.project.banking_api.src.mappers.UserMapper;
import com.project.banking_api.src.services.repositories.BankAccountRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.UUID;

@Repository
public class BankAccountRepositoryImpl implements BankAccountRepository {

    private BankAccountRepositoryDB bankAccountRepository;
    private UserMapper userMapper;
    private BankMapper bankMapper;

    public BankAccountRepositoryImpl(BankAccountRepositoryDB bankAccountRepository, UserMapper userMapper, BankMapper bankMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.userMapper = userMapper;
        this.bankMapper = bankMapper;
    }

    @Override
    public BankAccount create(User user) {

        BankAccountModel bankAccountModel = new BankAccountModel();
        bankAccountModel.setBalance(new BigDecimal("00.00"));
        bankAccountModel.setUser(userMapper.toModel(user));

        BankAccountModel newBank = bankAccountRepository.save(bankAccountModel);

        return bankMapper.toEntity(newBank);
    }

    @Override
    public BankAccount addBalance(UUID bankAccount, BigDecimal balance) {
        BankAccountModel accountReceive = bankAccountRepository.findById(bankAccount).orElseThrow(() -> new NotFoundException("Account Bank Not Found"));

        accountReceive.setBalance(accountReceive.getBalance().add(balance));

        bankAccountRepository.save(accountReceive);

        return bankMapper.toEntity(accountReceive);
    }

    @Override
    public BankAccount findByUserID(UUID userID) {

        BankAccountModel findBank = bankAccountRepository.findByUserId(userID);

        if(findBank == null){
            throw new NotFoundException("Account Bank Not Found");
        }

        return bankMapper.toEntity(findBank);

    }

    @Override
    public BankAccount findByID(UUID accountID) {

        BankAccountModel findBank = bankAccountRepository.findById(accountID).orElseThrow(() -> new NotFoundException("Account Bank Not Found"));

        return bankMapper.toEntity(findBank);
    }

    @Override
    public BankAccount subtractBalance(UUID bankAccount, BigDecimal balance) {
        BankAccountModel accountSubtracted = bankAccountRepository.findById(bankAccount).orElseThrow(() -> new NotFoundException("Account Bank Not Found"));

        accountSubtracted.setBalance(accountSubtracted.getBalance().subtract(balance));

        bankAccountRepository.save(accountSubtracted);

        return bankMapper.toEntity(accountSubtracted);
    }
}
