package com.db.security.service.impl;

import com.db.security.config.DataSourceContextHolder;
import com.db.security.config.DatasourceType;
import com.db.security.mapper.LuuTru_PassportMapper;
import com.db.security.mapper.PassportMapper;
import com.db.security.model.PassportEntity;
import com.db.security.repository.PassportRepository;
import com.db.security.request.PassportRequest;
import com.db.security.response.LuuTru_PassportResponse;
import com.db.security.response.PassportResponse;
import com.db.security.service.PassportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PassportServiceImpl implements PassportService {

    private final PassportRepository passportRepository;
    private final PassportMapper passportMapper;
    private final DataSourceContextHolder dataSourceContextHolder;


    @Override
    public List<PassportResponse> getAllPassport() {
        return passportRepository.findAll().stream().map(passportMapper::toResponse).toList();
    }

//    @Override
//    public List<LuuTru_PassportResponse> getListLuuTru_Passport() {
//        dataSourceContextHolder.setBranchContext(DatasourceType.LUU_TRU);
//        return passportRepository.findAll().stream().map(luupassportMapper::toResponse).toList();
//    }

    @Override
    public long addPassport(PassportRequest request) {
        PassportEntity passportEntity = passportMapper.toEntity(request);
        PassportEntity savedPassport = passportRepository.save(passportEntity);
        return savedPassport.getId();
    }

    @Override
    public List<String> findPassportIDsByResidentId(long residentId) {
        return passportRepository.findPassportIDsByResidentId(residentId);
    }

    @Transactional
    @Override
    public void updateEnddate(long id, LocalDate endDate) {
        dataSourceContextHolder.setBranchContext(DatasourceType.LUU_TRU);
        PassportEntity passportEntity = passportRepository.findById(id).orElseThrow(RuntimeException::new);
        passportRepository.updateEndDate(endDate, passportEntity.getId());
    }
}