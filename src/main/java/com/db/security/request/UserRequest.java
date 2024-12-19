package com.db.security.request;

import com.db.security.model.enum_type.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private long id;
    private Long locationId;
    private String passportId;

    private String fullName;

    private Gender gender;

    private String CCCD;

    private String phone;

    private String email;

    private int isAuthenticated;
    private int isApprove;
    private int isRejected;
    private String note;


}
