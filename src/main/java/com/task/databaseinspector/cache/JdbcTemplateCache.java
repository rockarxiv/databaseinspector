package com.task.databaseinspector.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.task.databaseinspector.busobj.dto.ConnectionDto;
import com.task.databaseinspector.service.ConnectionService;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class JdbcTemplateCache {
    private static final String URL_TEMPLATE = "jdbc:postgresql://%1$s:%2$s/%3$s";

    @Value("${jdbc.template.cache.size}")
    private int cacheSize;

    @Value("${jdbc.template.cache.expire.after.access.seconds}")
    private int expireAfterAccessSeconds;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClass;

    @Value("${user.defined.db.connection.pool.size}")
    private int userDefinedConnectionsPoolSize;

    private Cache<Long, JdbcTemplate> jdbcTemplateCache;

    @PostConstruct
    private void initCache() {
        jdbcTemplateCache = CacheBuilder.newBuilder()
                .maximumSize(cacheSize)
                .expireAfterAccess(expireAfterAccessSeconds, TimeUnit.SECONDS)
                .removalListener(notification -> {
                    DataSource dataSource = ((JdbcTemplate) notification.getValue()).getDataSource();
                    if (dataSource != null) {
                        ((HikariDataSource) dataSource).close();
                    }
                })
                .build();
    }

    @Autowired
    private ConnectionService connectionService;


    public JdbcTemplate getJdbcTemplate(Long connectionEntityId) {
        JdbcTemplate jdbcTemplate;
        try {
            jdbcTemplate = jdbcTemplateCache.get(connectionEntityId, () -> {
                ConnectionDto connectionDto = connectionService.get(connectionEntityId);
                return new JdbcTemplate(createNewPool(connectionDto));
            });
            return jdbcTemplate;
        } catch (ExecutionException e) {
            log.error("Error while trying to get/create jdbcTemplate for connection.id={}", connectionEntityId, e);
            return null;
        }
    }

    public void removeFromCache(Long connectionEntityId) {
        jdbcTemplateCache.invalidate(connectionEntityId);
    }

    private DataSource createNewPool(ConnectionDto connectionDto) {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driverClass);
        config.setUsername(connectionDto.getUsername());
        config.setPassword(connectionDto.getPassword());
        config.setJdbcUrl(String.format(URL_TEMPLATE, connectionDto.getHost(), connectionDto.getPort(), connectionDto.getDatabaseName()));
        config.setMaximumPoolSize(userDefinedConnectionsPoolSize);
        return new HikariDataSource(config);
    }

}
