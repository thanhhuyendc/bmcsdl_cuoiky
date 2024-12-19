package com.db.security.repository;


import com.db.security.model.UserRequestEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface UserRequestRepository extends JpaRepository<UserRequestEntity, Long> {
    @Query("SELECT u FROM UserRequestEntity u WHERE u.passport.passportID= :passportId")
    UserRequestEntity findUserRequestByPassport(@Param("passportId") String passportId);

    @Query("SELECT u FROM UserRequestEntity u WHERE u.isApprove=0 AND u.isRejected=0")
    List<UserRequestEntity> getAllUserUnverified();
    @Modifying
    @Query(value = "update passport.userrequest set ISAPPROVE = 1 where id = ?", nativeQuery=true)
    void Approve(Long id);
    @Modifying
    @Query(value = "UPDATE passport.userrequest SET ISREJECTED = 1, NOTE = :note WHERE id = :id", nativeQuery = true)
    void NoApprove(@Param("id") Long id,@Param("note") String note);
    @Modifying
    @Query(value = "update passport.userrequest set ISAUTHENTICATED = 1 where id = ?", nativeQuery=true)
    void Authen(Long id);
    @Modifying
    @Query(value = "UPDATE passport.userrequest SET ISAUTHENTICATED = -1, NOTE = :note WHERE id = :id", nativeQuery = true)
    void NoAuthen(@Param("id") Long id,@Param("note") String note);
    @Query("SELECT u FROM UserRequestEntity u")
    List<UserRequestEntity> getAllUsers();

    @Modifying
    @Query("update UserRequestEntity  set isApprove = 1 where id = ?1")
    void ApproveUserRequest(Long id);

    @Modifying
    @Query("update UserRequestEntity  set isRejected = 1 where id = ?1")
    void RejectUserRequest(Long id);
}