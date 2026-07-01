package com.project.banking_api.src.services.repositories;

import com.project.banking_api.src.entities.Transfer;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository {

    Transfer transferBalance(Transfer transfer);

}
