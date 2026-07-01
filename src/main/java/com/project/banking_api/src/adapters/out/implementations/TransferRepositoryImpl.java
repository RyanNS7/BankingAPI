package com.project.banking_api.src.adapters.out.implementations;

import com.project.banking_api.src.entities.Transfer;
import com.project.banking_api.src.exceptions.NotFoundException;
import com.project.banking_api.src.infra.models.BankAccountModel;
import com.project.banking_api.src.infra.models.TransferModel;
import com.project.banking_api.src.infra.repositories.BankAccountRepositoryDB;
import com.project.banking_api.src.infra.repositories.TransferModelDB;
import com.project.banking_api.src.mappers.TransferMapper;
import com.project.banking_api.src.services.repositories.TransferRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TransferRepositoryImpl implements TransferRepository {

    private BankAccountRepositoryDB bankAccountRepository;
    private TransferMapper transferMapper;
    private TransferModelDB transferModelDB;

    public TransferRepositoryImpl(BankAccountRepositoryDB bankAccountRepository, TransferMapper transferMapper, TransferModelDB transferModelDB) {
        this.bankAccountRepository = bankAccountRepository;
        this.transferMapper = transferMapper;
        this.transferModelDB = transferModelDB;
    }

    @Override
    public Transfer transferBalance(Transfer transfer) {

        BankAccountModel accountSent = bankAccountRepository.findById( transfer.getAccountSent()).orElseThrow(() -> new NotFoundException("Account Bank Not Found"));
        BankAccountModel accountReceived = bankAccountRepository.findById(transfer.getAccountReceived()).orElseThrow(() -> new NotFoundException("Account Bank Not Found"));

        TransferModel transferModel = transferMapper.toModel(transfer);
        transferModel.setAccountReceived(accountReceived);
        transferModel.setAccountSent(accountSent);

        TransferModel save = transferModelDB.save(transferModel);

        return transferMapper.toEntity(save);
    }

}
