package com.db.security.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(schema = "SYS", name = "audit_trail_view")
@Accessors(chain = true)
public class AuditEntity {
    @Id
    @Column(name = "TIMESTAMP", nullable = false)
    private String TIMESTAMP;

    @Column(name = "USERNAME")
    private String USERNAME;

    @Column(name = "ACTION_NAME")
    private String ACTION_NAME;

    @Column(name = "OBJ_NAME")
    private String OBJ_NAME;

    @Column(name = "SQL_TEXT")
    private String SQL_TEXT;
}
