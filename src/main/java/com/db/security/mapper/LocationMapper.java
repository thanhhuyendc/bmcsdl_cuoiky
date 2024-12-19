package com.db.security.mapper;

import com.db.security.model.LocationEntity;
import com.db.security.response.LocationResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    LocationResponse toResponse(LocationEntity entity);
}
