package com.db.security.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PassportRequest {
    private long id;
    private long residentId;
    private String passportID;
    private LocalDate startDate;
    private LocalDate endDate;
    private int isXD;
}
