package com.project.banking_api.src.infra.repositories;

import com.project.banking_api.src.infra.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepositoryDB extends JpaRepository<UserModel, UUID> {

    UserModel findByEmail(String email);

}
