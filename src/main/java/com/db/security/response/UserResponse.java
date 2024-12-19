package com.db.security.response;

import com.db.security.model.enum_type.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private long id;

    private String fullName;

    private Gender gender;

    private String CCCD;

    private String phone;

    private String email;

    private int isAuthenticated;

    private int isApprove;

    private int isRejected;

    private String note;
    private PassportResponse passport;

    private LocationResponse location;
}
