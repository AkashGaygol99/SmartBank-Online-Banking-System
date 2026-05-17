package com.smartbank.service;import com.smartbank.entity.AuditLog;import com.smartbank.repository.AuditLogRepository;import lombok.RequiredArgsConstructor;import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor

public class AuditService{
    private final AuditLogRepository repo;

    public void log(String actor,String action,String details){
        repo.save(AuditLog.builder().actorEmail(actor).action(action).details(details).build());
    }
}
