package com.db.security.mapper;

import com.db.security.model.AuditEntity;
import com.db.security.model.PassportEntity;
import com.db.security.request.PassportRequest;
import com.db.security.response.AuditResponse;
import com.db.security.response.PassportResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuditMapper {
    AuditResponse toResponse(AuditEntity entity);
}
