package com.db.security.mapper;

import com.db.security.model.ResidentEntity;
import com.db.security.response.ResidentResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResidentMapper {
    ResidentResponse toResponse(ResidentEntity entity);
}
