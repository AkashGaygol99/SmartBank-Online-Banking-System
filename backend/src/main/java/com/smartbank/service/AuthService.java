package com.smartbank.service;

import com.smartbank.dto.AuthDtos.*;
import com.smartbank.entity.*;
import com.smartbank.repository.*;
import com.smartbank.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository users;
    private final AccountRepository accounts;
    private final PasswordEncoder encoder;
    private final JwtService jwt;
    private final AuthenticationManager auth;

    @Transactional
    public AuthResponse register(RegisterRequest r) {

        if (users.existsByEmail(r.email())) {
            throw new RuntimeException("Email already registered");
        }

        User u = User.builder()
                .name(r.name())
                .email(r.email())
                .password(encoder.encode(r.password()))
                .role(Role.ROLE_CUSTOMER)
                .build();

        u = users.save(u);

        Account account = Account.builder()
                .user(u)
                .accountNumber("SB" + System.currentTimeMillis())
                .balance(BigDecimal.valueOf(1000))
                .build();

        accounts.save(account);

        return new AuthResponse(
                jwt.generateToken(u.getEmail()),
                u.getName(),
                u.getEmail(),
                u.getRole().name()
        );
    }

    public AuthResponse login(LoginRequest r) {

        auth.authenticate(
                new UsernamePasswordAuthenticationToken(
                        r.email(),
                        r.password()
                )
        );

        User u = users.findByEmail(r.email()).orElseThrow();

        return new AuthResponse(
                jwt.generateToken(u.getEmail()),
                u.getName(),
                u.getEmail(),
                u.getRole().name()
        );
    }
}