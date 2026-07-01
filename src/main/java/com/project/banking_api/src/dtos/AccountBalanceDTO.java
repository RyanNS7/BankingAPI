package com.project.banking_api.src.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public record AccountBalanceDTO(UUID accountReceive, BigDecimal balance) {
}
