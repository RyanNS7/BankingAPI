package com.project.banking_api.src.services.repositories;

import com.project.banking_api.src.entities.User;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository {

    User create(User user);
    User findByID(UUID id);
    User findByEmail(String email);

}
