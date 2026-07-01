package com.project.banking_api.src.infra.repositories;

import com.project.banking_api.src.infra.models.TransferModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransferModelDB extends JpaRepository<TransferModel, UUID> {
}
