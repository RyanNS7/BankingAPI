package com.project.banking_api.src.adapters.in.controllers;

import com.project.banking_api.src.dtos.LoginRequestDTO;
import com.project.banking_api.src.dtos.RegisterRequestDTO;
import com.project.banking_api.src.dtos.RegisterResponseDTO;
import com.project.banking_api.src.mappers.UserMapper;
import com.project.banking_api.src.services.usecases.login.LoginUserService;
import com.project.banking_api.src.services.usecases.login.RegisterUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

    private RegisterUserService registerUserService;

    private LoginUserService loginUserService;

    private UserMapper userMapper;

    public AuthController(RegisterUserService registerUserService, LoginUserService loginUserService, UserMapper userMapper) {
        this.registerUserService = registerUserService;
        this.loginUserService = loginUserService;
        this.userMapper = userMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterRequestDTO registerRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUserService.register(userMapper.toEntity(registerRequestDTO)));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequestDTO){
        return  ResponseEntity.ok(loginUserService.login(loginRequestDTO));
    }

}
