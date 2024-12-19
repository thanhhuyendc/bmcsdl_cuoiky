package com.db.security.service;

import com.db.security.model.PassportEntity;
import com.db.security.request.PassportRequest;
import com.db.security.request.UserRequest;
import com.db.security.response.LuuTru_PassportResponse;
import com.db.security.response.PassportResponse;

import java.time.LocalDate;
import java.util.List;

public interface PassportService {
    List<PassportResponse> getAllPassport();
//    List<LuuTru_PassportResponse> getListLuuTru_Passport();
    long addPassport(PassportRequest request);
    List<String> findPassportIDsByResidentId(long residentId);
    void updateEnddate(long id, LocalDate endDate);
}