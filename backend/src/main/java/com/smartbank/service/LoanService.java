package com.smartbank.service;import com.smartbank.dto.AuthDtos.*;import com.smartbank.entity.*;import com.smartbank.repository.*;import lombok.RequiredArgsConstructor;import org.springframework.stereotype.Service;import java.util.*;
@Service
@RequiredArgsConstructor
public class LoanService{
    private final LoanRepository loans;
    private final UserRepository users;
    public Loan apply(String email,LoanRequest r){
        var u=users.findByEmail(email).orElseThrow();
        return loans.save(Loan.builder().
                user(u).
                amount(r.amount()).
                tenureMonths(r.tenureMonths()).purpose(r.purpose()).status(LoanStatus.PENDING).build());
    }

    public List<Loan> mine(String email){
        return loans.findByUserEmailOrderByCreatedAtDesc(email);
    }

    public List<Loan> all(){
        return loans.findAll();
    }

    public Loan status(Long id,LoanStatus s){
        var l=loans.findById(id).orElseThrow();
        l.setStatus(s);
        return loans.save(l);
    }
}
