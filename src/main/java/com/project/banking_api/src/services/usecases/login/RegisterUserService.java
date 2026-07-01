package com.project.banking_api.src.services.usecases.login;

import com.project.banking_api.src.dtos.RegisterResponseDTO;
import com.project.banking_api.src.entities.BankAccount;
import com.project.banking_api.src.entities.User;
import com.project.banking_api.src.enums.Roles;
import com.project.banking_api.src.exceptions.NotFoundException;
import com.project.banking_api.src.exceptions.UserAlreadyExistsException;
import com.project.banking_api.src.services.repositories.BankAccountRepository;
import com.project.banking_api.src.services.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterUserService {

    private UserRepository userRepo;
    private BankAccountRepository bankAccountRepo;
    private PasswordEncoder passwordEncoder;

    public RegisterUserService(UserRepository userRepo, BankAccountRepository bankAccountRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.bankAccountRepo = bankAccountRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public RegisterResponseDTO register(User user){

        user.setRole(Roles.CLIENT);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User createUser = userRepo.create(user);
        BankAccount createAccount = bankAccountRepo.create(createUser);

        return new RegisterResponseDTO(createUser, createAccount);
    }
}
