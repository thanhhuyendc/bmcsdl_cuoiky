package com.db.security.service.impl;

import com.db.security.config.DataSourceContextHolder;
import com.db.security.config.DatasourceType;
import com.db.security.repository.EmployeeRepository;
import com.db.security.request.EmployeeRequest;
import com.db.security.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DataSourceContextHolder dataSourceContextHolder;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public String getEmployee(EmployeeRequest request) {
        String sql = "{call passport.validate_login(?, ?, ?, ?, ?)}";

        return jdbcTemplate.execute((Connection conn) -> {
            try (CallableStatement callableStatement = conn.prepareCall(sql)) {
                callableStatement.setString(1, request.getUsername());
                callableStatement.setBytes(2, request.getPassword().getBytes());

                callableStatement.registerOutParameter(3, Types.NUMERIC);
                callableStatement.registerOutParameter(4, Types.NUMERIC);
                callableStatement.registerOutParameter(5, Types.VARCHAR);

                callableStatement.execute();

                int result = callableStatement.getInt(3);
                String role = callableStatement.getString(5);
                if (result == 1) {
                    switch (role) {
                        case "BPXacThuc" -> dataSourceContextHolder.setBranchContext(DatasourceType.XAC_THUC);
                        case "BPXetDuyet" -> dataSourceContextHolder.setBranchContext(DatasourceType.XET_DUYET);
                        case "BPLuuTru" -> dataSourceContextHolder.setBranchContext(DatasourceType.LUU_TRU);
                        case "BPGiamSat" -> dataSourceContextHolder.setBranchContext(DatasourceType.GIAM_SAT);
                    }
                    return employeeRepository.findByUsername(request.getUsername()).getUsername();
                } else {
                    return null;
                }

            } catch (Exception e) {
                return null;
            }
        });
    }

}
