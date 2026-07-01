package com.project.banking_api.src.dtos;

import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;

public record RegisterRequestDTO(@CPF String cpf, @Email String email, String name, String password) {
}
