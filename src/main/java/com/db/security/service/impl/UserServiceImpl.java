package com.db.security.service.impl;

import com.db.security.config.DataSourceContextHolder;
import com.db.security.config.DatasourceType;
import com.db.security.mapper.LocationMapper;
import com.db.security.mapper.UserMapper;
import com.db.security.model.LocationEntity;
import com.db.security.model.PassportEntity;
import com.db.security.model.UserRequestEntity;
import com.db.security.repository.LocationRepository;
import com.db.security.repository.PassportRepository;
import com.db.security.repository.UserRequestRepository;
import com.db.security.response.LocationResponse;
import com.db.security.request.UserRequest;
import com.db.security.response.UserResponse;
import com.db.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final LocationRepository locationRepository;
    private final UserMapper userMapper;
    private final UserRequestRepository userRequestRepository;
    private final DataSourceContextHolder dataSourceContextHolder;
    private final LocationMapper locationMapper;
    private final PassportRepository passportRepository;


    @Override
    public void addUser(UserRequest request) {
        UserRequestEntity entity = userMapper.toEntity(request);
        PassportEntity passportEntity = passportRepository.findByPassportID(request.getPassportId()); // Fetch from repository
        LocationEntity locationEntity = locationRepository.findById(request.getLocationId())
                .orElseThrow(() -> new RuntimeException("Location not found"));
        entity.setPassport(passportEntity);
        entity.setLocation(locationEntity);
        userRequestRepository.save(entity);
    }

    @Override
    public List<LocationResponse> getListLocation() {
        return locationRepository.findAll().stream().map(locationMapper::toResponse).toList();
    }

    @Override
    public UserResponse getUser(String passportId) {
        return userMapper.toResponse(userRequestRepository.findUserRequestByPassport(passportId));
    }


    @Override
    public List<UserResponse> getAllUser() {
        List<UserRequestEntity> userRequestEntities = userRequestRepository.getAllUsers();
        return userRequestEntities.stream().map(userMapper::toResponse).toList();
    }




    @Override
    public void Approve(long id) {
        userRequestRepository.Approve(id);
    }

    @Override
    public void NoApprove(long id,String note) {
        userRequestRepository.NoApprove(id,note);
    }

    @Override
    public void Authen(long id) {
        userRequestRepository.Authen(id);
    }

    @Override
    public void NoAuthen(long id,String note) {
        userRequestRepository.NoAuthen(id,note);
    }

    @Override
    public void ApproveUserRequest(long id, String username) {
        dataSourceContextHolder.setBranchContext(DatasourceType.XET_DUYET);
        try{
            userRequestRepository.ApproveUserRequest(id);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void RejectUserRequest(long id, String username) {
        switch(username){
            case "XacThuc" -> dataSourceContextHolder.setBranchContext(DatasourceType.XAC_THUC);
            case "XetDuyet" -> dataSourceContextHolder.setBranchContext(DatasourceType.XET_DUYET);
        }
        try{
            userRequestRepository.ApproveUserRequest(id);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}