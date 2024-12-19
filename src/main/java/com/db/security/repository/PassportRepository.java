package com.db.security.repository;

import com.db.security.model.PassportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
public interface PassportRepository extends JpaRepository<PassportEntity, Long> {
    PassportEntity findByPassportID(String passportId);
    @Modifying
    @Query(value = "update passport.passport set end_date = :endDate where id = :id", nativeQuery=true)
    void updateEndDate(LocalDate endDate, Long id);

    @Query("SELECT p.passportID FROM PassportEntity p WHERE p.resident.id = :residentId AND p.isXD = 1")
    List<String> findPassportIDsByResidentId(@Param("residentId") long residentId);
}
