package com.project.banking_api.src.adapters.in.controllers;

import com.project.banking_api.src.dtos.AccountBalanceDTO;
import com.project.banking_api.src.entities.BankAccount;
import com.project.banking_api.src.entities.Transfer;
import com.project.banking_api.src.security.UserDetailsImpl;
import com.project.banking_api.src.services.usecases.bankAccount.AddBalanceService;
import com.project.banking_api.src.services.usecases.transfer.TransferBalanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bank")
public class BankController {

    private AddBalanceService addBalanceService;

    private TransferBalanceService transferBalanceService;

    public BankController(AddBalanceService addBalanceService, TransferBalanceService transferBalanceService) {
        this.addBalanceService = addBalanceService;
        this.transferBalanceService = transferBalanceService;
    }

    @PostMapping("/balance/add")
    public ResponseEntity<BankAccount> add(@RequestBody AccountBalanceDTO accountBalanceDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(addBalanceService.addBalance(accountBalanceDTO.accountReceive(), accountBalanceDTO.balance()));
    }

    @PostMapping("/transfer")
    public ResponseEntity<Transfer> transfer(@RequestBody AccountBalanceDTO accountBalanceDTO, @AuthenticationPrincipal UserDetailsImpl user){
        return ResponseEntity.ok().body(transferBalanceService.transfer(accountBalanceDTO, user.getID()));
    }
}
