package com.db.security.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequest {
    private long id;
    private long residenceId;
    private String username;
    private String password;
    private String role;
}
