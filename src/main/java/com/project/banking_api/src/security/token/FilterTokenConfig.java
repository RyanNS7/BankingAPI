package com.project.banking_api.src.security.token;

import com.project.banking_api.src.entities.User;
import com.project.banking_api.src.exceptions.NotFoundException;
import com.project.banking_api.src.exceptions.UnauthorizedException;
import com.project.banking_api.src.security.UserDetailsImpl;
import com.project.banking_api.src.services.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FilterTokenConfig extends OncePerRequestFilter {

    private final TokenUtils tokenUtils;
    private final UserRepository userRepo;

    public FilterTokenConfig(TokenUtils tokenUtils, UserRepository userRepo) {
        this.tokenUtils = tokenUtils;
        this.userRepo = userRepo;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try{

            String token = request.getHeader("Authorization");

            if(token == null || !token.startsWith("Bearer ")){
                filterChain.doFilter(request, response);
                return;
            }

            Claims claims = tokenUtils.extractClaims(token);

            String email = claims.get("email", String.class);

            User user = userRepo.findByEmail(email);

            if(user == null){
                throw new UnauthorizedException("Unauthorized token");
            }

            UserDetailsImpl userDetails = new UserDetailsImpl(user);

            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            SecurityContext context = SecurityContextHolder.createEmptyContext();

            context.setAuthentication(authentication);

            SecurityContextHolder.setContext(context);

            filterChain.doFilter(request, response);
        }catch (Exception e){
            response.setStatus(401);
            System.out.println(e.getMessage());
            return ;
        }

    }
}
