package com.smartbank.controller;
import com.smartbank.dto.AuthDtos.*;
import com.smartbank.entity.Loan;
import com.smartbank.service.LoanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("/loans")
@RequiredArgsConstructor
public class LoanController{
    private final LoanService loans;

    @PostMapping public Loan apply(Principal p,@Valid @RequestBody LoanRequest r){
        return loans.apply(p.getName(),r);
    }

    @GetMapping public List<Loan> mine(Principal p){
        return loans.mine(p.getName());
    }
}
