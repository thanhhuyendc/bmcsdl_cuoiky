package com.db.security.response;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class AuditResponse {
    private String TIMESTAMP;
    private String USERNAME;
    private String ACTION_NAME;
    private String OBJ_NAME;
    private String SQL_TEXT;

}
