package com.db.security.service;

import com.db.security.response.LocationResponse;
import com.db.security.request.UserRequest;
import com.db.security.response.UserResponse;

import java.util.List;

public interface UserService {
    void addUser(UserRequest request);
    List<LocationResponse> getListLocation();
    UserResponse getUser(String passportId);
    List<UserResponse> getAllUser();
    void Approve(long id);
    void NoApprove(long id,String note);
    void Authen(long id);
    void NoAuthen(long id,String note);
    void ApproveUserRequest(long id, String username);
    void RejectUserRequest(long id, String username);
}