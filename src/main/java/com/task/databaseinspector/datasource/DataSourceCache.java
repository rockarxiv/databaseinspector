package com.task.databaseinspector.datasource;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.task.databaseinspector.busobj.dto.ConnectionDto;
import com.task.databaseinspector.service.ConnectionService;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class DataSourceCache {
    private static final String URL_TEMPLATE = "jdbc:postgresql://%1$s:%2$s/%3$s";

    @Value("${user.defined.db.cache.size}")
    private int cacheSize;

    @Value("${user.defined.db.cache.expire.after.access.seconds}")
    private int expireAfterAccessSeconds;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClass;

    @Value("${user.defined.db.connection.pool.size}")
    private int userDefinedConnectionsPoolSize;

    private Cache<Long, DataSource> dataSourceCache;

    @PostConstruct
    private void initCache() {
        dataSourceCache = CacheBuilder.newBuilder()
                .maximumSize(cacheSize)
                .expireAfterAccess(expireAfterAccessSeconds, TimeUnit.SECONDS)
                .removalListener(notification -> {
                    ((HikariDataSource) notification.getValue()).close();
                })
                .build();
    }

    @Autowired
    @Lazy
    private ConnectionService connectionService;


    public DataSource getDataSource(Long connectionEntityId) {
        DataSource dataSource;
        try {
            dataSource = dataSourceCache.get(connectionEntityId, () -> {
                ConnectionDto connectionDto = connectionService.get(connectionEntityId);
                return createNewPool(connectionDto);
            });
            return dataSource;
        } catch (ExecutionException e) {
            log.error("Error while trying to get/create dataSource for connection.id={}", connectionEntityId, e);
            return null;
        }
    }

    public void removeFromCache(Long connectionEntityId) {
        dataSourceCache.invalidate(connectionEntityId);
    }

    private DataSource createNewPool(ConnectionDto connectionDto) {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driverClass);
        config.setUsername(connectionDto.getUsername());
        config.setPassword(connectionDto.getPassword());
        config.setJdbcUrl(String.format(URL_TEMPLATE, connectionDto.getHost(), connectionDto.getPort(), connectionDto.getDatabaseName()));
        config.setMaximumPoolSize(userDefinedConnectionsPoolSize);
        config.setReadOnly(true);
        return new HikariDataSource(config);
    }

}
