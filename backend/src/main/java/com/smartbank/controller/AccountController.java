package com.smartbank.controller;import com.smartbank.dto.AuthDtos.*;import com.smartbank.entity.*;import com.smartbank.service.BankService;import jakarta.validation.Valid;import lombok.RequiredArgsConstructor;import org.springframework.web.bind.annotation.*;import java.security.Principal;import java.util.*;
@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController{
    private final BankService bank;

    @GetMapping
    public Account account(Principal p){
        return bank.myAccount(p.getName());
    }

    @GetMapping("/transactions")
    public List<BankTransaction> history(Principal p){
        return bank.history(p.getName());
    }

    @PostMapping("/transfer")
    public MessageResponse transfer(Principal p,@Valid @RequestBody TransferRequest r){
        return bank.transfer(p.getName(),r);
    }
}
