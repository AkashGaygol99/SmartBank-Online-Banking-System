package com.smartbank.controller;
import com.smartbank.entity.*;
import com.smartbank.repository.*;
import com.smartbank.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController{
    private final UserRepository users;
    private final AccountRepository accounts;
    private final TransactionRepository txns;
    private final LoanService loans;

    @GetMapping("/users")
    public List<User> users(){
        return users.findAll();
    }

    @GetMapping("/accounts")
    public List<Account> accounts(){
        return accounts.findAll();
    }

    @GetMapping("/transactions")
    public List<BankTransaction> transactions(){
        return txns.findAll();
    }

    @GetMapping("/loans")
    public List<Loan> loans(){
        return loans.all();
    }

    @PutMapping("/loans/{id}/{status}")
    public Loan updateLoan(@PathVariable Long id,@PathVariable LoanStatus status){
        return loans.status(id,status);
    }

    @PutMapping("/accounts/{id}/freeze")
    public Account freeze(@PathVariable Long id,@RequestParam boolean value){
        var a=accounts.findById(id).orElseThrow();a.setFrozen(value);
        return accounts.save(a);
    }

    @GetMapping("/dashboard")
    public Map<String,Object> dashboard(){
        return Map.of("users",users.count(),"accounts",accounts.count(),"transactions",txns.count(),"loans",loans.all().size());
    }
}
