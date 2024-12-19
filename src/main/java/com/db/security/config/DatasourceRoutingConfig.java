package com.db.security.config;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Component
public class DatasourceRoutingConfig extends AbstractRoutingDataSource {
    private final DataSourceContextHolder dataSourceContextHolder;
    private final PrimaryDatasourceConfig primaryDatasourceConfig;
    private final XacThucDatasourceConfig xacThucDatasourceConfig;
    private final LuuTruDatasourceConfig luuTruDatasourceConfig;
    private final XetDuyetDatasourceConfig xetDuyetDatasourceConfig;
    private final NguoiDungDatasourceConfig nguoiDungDatasourceConfig;
    private final GiamSatDatasourceConfig giamSatDatasourceConfig;

    public DatasourceRoutingConfig(DataSourceContextHolder dataSourceContextHolder, PrimaryDatasourceConfig primaryDatasourceConfig, XacThucDatasourceConfig xacThucDatasourceConfig,
                                    LuuTruDatasourceConfig luuTruDatasourceConfig, XetDuyetDatasourceConfig xetDuyetDatasourceConfig, NguoiDungDatasourceConfig nguoiDungDatasourceConfig, GiamSatDatasourceConfig giamSatDatasourceConfig) {
        this.dataSourceContextHolder = dataSourceContextHolder;
        this.primaryDatasourceConfig = primaryDatasourceConfig;
        this.xacThucDatasourceConfig = xacThucDatasourceConfig;
        this.luuTruDatasourceConfig = luuTruDatasourceConfig;
        this.xetDuyetDatasourceConfig = xetDuyetDatasourceConfig;
        this.nguoiDungDatasourceConfig = nguoiDungDatasourceConfig;
        this.giamSatDatasourceConfig = giamSatDatasourceConfig;

        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DatasourceType.PRIMARY, primaryDatasourceConfig());
        dataSourceMap.put(DatasourceType.XAC_THUC, xacThucDatasourceConfig());
        dataSourceMap.put(DatasourceType.XET_DUYET, xetDuyetDatasourceConfig());
        dataSourceMap.put(DatasourceType.LUU_TRU, luuTruDatasourceConfig());
        dataSourceMap.put(DatasourceType.NGUOI_DUNG, nguoiDungDatasourceConfig());
        dataSourceMap.put(DatasourceType.GIAM_SAT, giamSatDatasourceConfig());

        this.setTargetDataSources(dataSourceMap);
        this.setDefaultTargetDataSource(xacThucDatasourceConfig());
    }

    private DataSource nguoiDungDatasourceConfig() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(nguoiDungDatasourceConfig.getUrl());
        dataSource.setUsername(nguoiDungDatasourceConfig.getUsername());
        dataSource.setPassword(nguoiDungDatasourceConfig.getPassword());
        return dataSource;
    }

    private DataSource xetDuyetDatasourceConfig() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(xetDuyetDatasourceConfig.getUrl());
        dataSource.setUsername(xetDuyetDatasourceConfig.getUsername());
        dataSource.setPassword(xetDuyetDatasourceConfig.getPassword());
        return dataSource;
    }
    private DataSource luuTruDatasourceConfig() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(luuTruDatasourceConfig.getUrl());
        dataSource.setUsername(luuTruDatasourceConfig.getUsername());
        dataSource.setPassword(luuTruDatasourceConfig.getPassword());
        return dataSource;
    }

    private DataSource primaryDatasourceConfig() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(primaryDatasourceConfig.getUrl());
        dataSource.setUsername(primaryDatasourceConfig.getUsername());
        dataSource.setPassword(primaryDatasourceConfig.getPassword());
        return dataSource;
    }

    private DataSource xacThucDatasourceConfig() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(xacThucDatasourceConfig.getUrl());
        dataSource.setUsername(xacThucDatasourceConfig.getUsername());
        dataSource.setPassword(xacThucDatasourceConfig.getPassword());
        return dataSource;
    }
    private DataSource giamSatDatasourceConfig() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(giamSatDatasourceConfig.getUrl());
        dataSource.setUsername(giamSatDatasourceConfig.getUsername());
        dataSource.setPassword(giamSatDatasourceConfig.getPassword());
        return dataSource;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return dataSourceContextHolder.getBranchContext();
    }
}
