package com.db.security.mapper;

import com.db.security.model.PassportEntity;
import com.db.security.model.UserRequestEntity;
import com.db.security.request.PassportRequest;
import com.db.security.request.UserRequest;
import com.db.security.response.PassportResponse;
import lombok.Getter;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PassportMapper {
    @Mapping(source = "residentId", target = "resident.id")
    PassportEntity toEntity(PassportRequest request);
    @Mapping(source = "resident.id", target = "residentID")
    PassportResponse toResponse(PassportEntity entity);
}
