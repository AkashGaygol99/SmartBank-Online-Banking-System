package com.smartbank.repository;
import com.smartbank.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface TransactionRepository extends JpaRepository<BankTransaction,Long>{
    List<BankTransaction> findByAccountOrderByCreatedAtDesc(Account account);
}
