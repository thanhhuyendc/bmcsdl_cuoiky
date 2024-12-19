package com.db.security.mapper;

import com.db.security.model.EmployeeEntity;
import com.db.security.response.EmployeeResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeResponse toResponse(EmployeeEntity entity);
}
