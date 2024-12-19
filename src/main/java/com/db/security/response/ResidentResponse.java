package com.db.security.response;

import com.db.security.model.LocationEntity;
import com.db.security.model.enum_type.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResidentResponse {
    private long id;
    private String fullName;
    private Gender gender;
    private String cccd;
    private String phone;
    private String email;
    private LocationResponse location;
}
