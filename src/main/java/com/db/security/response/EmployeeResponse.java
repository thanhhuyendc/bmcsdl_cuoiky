package com.db.security.response;

import com.db.security.model.enum_type.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeResponse {
    private long id;
    private long residentId;
    private String username;
    private String password;
    private Role role;
}
