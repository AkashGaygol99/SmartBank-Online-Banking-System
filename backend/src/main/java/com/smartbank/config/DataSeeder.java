package com.smartbank.config;
import com.smartbank.entity.*;
import com.smartbank.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
@Component
@RequiredArgsConstructor
public class DataSeeder{
    private final UserRepository users;
    private final AccountRepository accounts;
    private final PasswordEncoder encoder;

    @Bean
    CommandLineRunner seed(){
        return args->{
            if(!users.existsByEmail("admin@smartbank.com")){
                var u=users.save(User.builder()
                        .name("SmartBank Admin")
                        .email("admin@smartbank.com")
                        .password(encoder.encode("admin123"))
                        .role(Role.ROLE_ADMIN).build());
                accounts.save(Account.builder()
                        .user(u)
                        .accountNumber("SB9999999999")
                        .balance(BigDecimal.valueOf(100000)).build());
            }
        };
    }
}
