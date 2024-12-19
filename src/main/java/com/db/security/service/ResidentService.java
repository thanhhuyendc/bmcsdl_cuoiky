package com.db.security.service;

import com.db.security.response.ResidentResponse;

import java.util.List;

public interface ResidentService {
    List<ResidentResponse> getAllResident();
    ResidentResponse findOneResident(String CCCD);

    ResidentResponse findByID(long id);
}