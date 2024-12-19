package com.db.security.mapper;

import com.db.security.model.UserRequestEntity;
import com.db.security.request.UserRequest;
import com.db.security.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserRequestEntity toEntity(UserRequest request);

    UserResponse toResponse(UserRequestEntity entity);
}
