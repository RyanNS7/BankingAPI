package com.project.banking_api.src.services.usecases.login;

import com.project.banking_api.src.dtos.LoginRequestDTO;
import com.project.banking_api.src.security.UserDetailsImpl;
import com.project.banking_api.src.security.token.TokenUtils;
import com.project.banking_api.src.services.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginUserService {

    private AuthenticationManager authenticationManager;
    private TokenUtils tokenUtils;

    public LoginUserService(AuthenticationManager authenticationManager, TokenUtils tokenUtils) {
        this.authenticationManager = authenticationManager;
        this.tokenUtils = tokenUtils;
    }

    public String login(LoginRequestDTO loginRequest){

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password()));

        UserDetailsImpl userDetails = (UserDetailsImpl) authenticate.getPrincipal();

        String token = tokenUtils.generateToken(userDetails);

        return token;
    }
}
