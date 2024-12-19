package com.db.security.repository;

import com.db.security.model.ResidentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface ResidentRepository extends JpaRepository<ResidentEntity, Long> {
    ResidentEntity findFirstByLocation_Id(long locationId);
    Optional<ResidentEntity> findByCccd(String CCCD);
}
