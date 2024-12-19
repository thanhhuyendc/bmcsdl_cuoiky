package com.db.security.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PassportResponse {
    private long id;
    private long residentID;
    private String passportID;
    private int isXD;
    private LocalDate startDate;
    private LocalDate endDate;

}
