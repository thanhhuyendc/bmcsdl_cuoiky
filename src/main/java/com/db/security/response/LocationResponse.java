package com.db.security.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationResponse {
    private long id;
    private String nation;
    private String city;
    private String district;
}
