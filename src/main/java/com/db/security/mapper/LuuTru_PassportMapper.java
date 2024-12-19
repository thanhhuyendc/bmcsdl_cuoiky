package com.db.security.mapper;

import com.db.security.model.PassportEntity;
import com.db.security.response.LuuTru_PassportResponse;

import lombok.Getter;
import lombok.Setter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LuuTru_PassportMapper {
    LuuTru_PassportResponse toResponse(PassportEntity entity);
}