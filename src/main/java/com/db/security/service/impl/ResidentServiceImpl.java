package com.db.security.service.impl;

import com.db.security.config.DataSourceContextHolder;
import com.db.security.config.DatasourceType;
import com.db.security.mapper.ResidentMapper;
import com.db.security.model.ResidentEntity;
import com.db.security.repository.ResidentRepository;
import com.db.security.response.ResidentResponse;
import com.db.security.service.ResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResidentServiceImpl implements ResidentService {
    private final ResidentRepository residentRepository;
    private final ResidentMapper residentMapper;
    private final DataSourceContextHolder dataSourceContextHolder;

    @Override
    public List<ResidentResponse> getAllResident() {
        return residentRepository.findAll().stream().map(residentMapper::toResponse).toList();
    }

    @Override
    public ResidentResponse findOneResident(String CCCD) {
        ResidentEntity resp = residentRepository.findByCccd(CCCD).orElse(null);
        if (resp != null) {
            return residentMapper.toResponse(resp);
        }else{
            return null;
        }
    }

    @Override
    public ResidentResponse findByID(long id) {
        ResidentEntity resp = residentRepository.findById(id).orElse(null);
        if (resp != null) {
            return residentMapper.toResponse(resp);
        }else{
            return null;
        }
    }


}