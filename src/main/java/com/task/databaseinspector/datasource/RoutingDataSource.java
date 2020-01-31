package com.task.databaseinspector.datasource;

import com.task.databaseinspector.interceptor.ContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component("routingDataSource")
@Slf4j
public class RoutingDataSource extends AbstractDataSource {
    @Autowired
    private DataSourceCache dataSourceCache;

    @Autowired
    private ContextHolder contextHolder;


    @Override
    public Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return getDataSource().getConnection(username, password);
    }

    private DataSource getDataSource() throws SQLException {
        if (contextHolder.getConnectionId() != null) {
            final DataSource dataSource = dataSourceCache.getDataSource(contextHolder.getConnectionId());
            if (dataSource != null) {
                return dataSource;
            }
        } else {
            log.error("Connection id was not set in ContextHolder");
        }
        throw new SQLException("Database for connection.id=" + contextHolder.getConnectionId() + " wasn't created!");
    }
}
