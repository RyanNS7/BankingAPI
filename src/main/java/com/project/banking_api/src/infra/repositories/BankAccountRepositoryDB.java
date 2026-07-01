package com.project.banking_api.src.infra.repositories;

import com.project.banking_api.src.infra.models.BankAccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BankAccountRepositoryDB extends JpaRepository<BankAccountModel,UUID> {

    BankAccountModel findByUserId(UUID userId);

}
