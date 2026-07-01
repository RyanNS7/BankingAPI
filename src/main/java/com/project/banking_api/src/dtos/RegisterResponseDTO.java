package com.project.banking_api.src.dtos;

import com.project.banking_api.src.entities.BankAccount;
import com.project.banking_api.src.entities.User;

public record RegisterResponseDTO(User user, BankAccount bankAccount) {
}
