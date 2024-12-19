package com.db.security.repository;

import com.db.security.model.AuditEntity;
import com.db.security.model.PassportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AuditRepository extends JpaRepository<AuditEntity, Long> {
    @Query("SELECT a FROM AuditEntity a WHERE a.USERNAME = 'XETDUYET'")
    List<AuditEntity> getAll();
}
