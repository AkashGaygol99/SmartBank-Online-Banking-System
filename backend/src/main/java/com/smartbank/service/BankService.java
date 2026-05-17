package com.smartbank.service;
import com.smartbank.dto.AuthDtos.*;
import com.smartbank.entity.*;
import com.smartbank.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.security.Principal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class BankService{
    private final AccountRepository accounts;
    private final TransactionRepository txns;
    private final AuditService audit;

    public Account myAccount(String email){
        return accounts.findByUserEmail(email).orElseThrow(()->new RuntimeException("Account not found"));
    }

    public List<BankTransaction> history(String email){
        return txns.findByAccountOrderByCreatedAtDesc(myAccount(email));
    }

    @Transactional
    public MessageResponse transfer(String email,TransferRequest r){
        var from=myAccount(email);
        var to=accounts.findByAccountNumber(r.toAccountNumber()).orElseThrow(()->new RuntimeException("Receiver account not found"));
        if(from.isFrozen())
            throw new RuntimeException("Account is frozen");
        if(to.isFrozen())
            throw new RuntimeException("Receiver account is frozen");
        if(from.getBalance().compareTo(r.amount())<0)
            throw new RuntimeException("Insufficient balance");
        from.setBalance(from.getBalance().subtract(r.amount()));
        to.setBalance(to.getBalance().add(r.amount()));
        String ref="TXN"+System.currentTimeMillis();
        accounts.save(from);
        accounts.save(to);
        txns.save(BankTransaction.builder()
                .account(from)
                .referenceNo(ref)
                .type(TransactionType.DEBIT)
                .amount(r.amount())
                .description("Transfer to "+to.getAccountNumber()+" - "+r.description()).build());
        txns.save(BankTransaction.builder().account(to).referenceNo(ref)
                .type(TransactionType.CREDIT).amount(r.amount())
                .description("Received from "+from.getAccountNumber())
                .build());
        audit.log(email,"TRANSFER",ref+" amount "+r.amount());
        return new MessageResponse("Transfer successful. Ref: "+ref);
    }
}
