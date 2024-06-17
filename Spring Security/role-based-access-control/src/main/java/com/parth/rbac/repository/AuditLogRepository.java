package com.parth.rbac.repository;

import com.parth.rbac.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuditLogRepository extends JpaRepository<AuditLog, Integer> {
   Optional<List<AuditLog>> findByUserId(Integer userId);
   Optional<List<AuditLog>> findByAdminId(Integer adminId);
}
