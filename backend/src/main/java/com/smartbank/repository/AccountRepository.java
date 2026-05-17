package com.smartbank.repository;
import com.smartbank.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface AccountRepository extends JpaRepository<Account,Long>{
    Optional<Account> findByUserEmail(String email);
    Optional<Account> findByAccountNumber(String accountNumber);
}
