package com.smartbank.dto;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class AuthDtos{
 public record RegisterRequest(@NotBlank String name,@Email String email,@Size(min=6) String password){}
 public record LoginRequest(@Email String email,@NotBlank String password){}
 public record AuthResponse(String token,String name,String email,String role){}
 public record TransferRequest(@NotBlank String toAccountNumber,@DecimalMin("1.0") BigDecimal amount,String description){}
 public record LoanRequest(@DecimalMin("1000.0") BigDecimal amount,@Min(6) Integer tenureMonths,@NotBlank String purpose){}
 public record MessageResponse(String message){}
}
