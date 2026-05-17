package com.smartbank.repository;
import com.smartbank.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface LoanRepository extends JpaRepository<Loan,Long>{
    List<Loan> findByUserEmailOrderByCreatedAtDesc(String email);
}
