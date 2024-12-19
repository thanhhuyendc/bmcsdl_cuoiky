package com.db.security.response;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LuuTru_PassportResponse {
    private long id;
    private String passportID;
    private LocalDate startDate;
    private LocalDate endDate;
}