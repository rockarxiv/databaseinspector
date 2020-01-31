package com.task.databaseinspector.dao.routing.Impl;

import com.task.databaseinspector.dao.routing.DataDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSetMetaData;
import java.util.*;

@Repository
public class DataDaoImpl implements DataDao {

    private final JdbcTemplate jdbcTemplate;

    public DataDaoImpl(@Qualifier("routingDataSource") DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Page<Map<String, Object>> getData(String schema, String tableName, int page, int size) {
        String sql = "Select * from " + schema + "." + tableName + " LIMIT " + size + " OFFSET " + page * size;
        String countQuery = "Select count(1) as row_count from " + schema + "." + tableName;
        Long total = jdbcTemplate.queryForObject(countQuery,
                    (rs, rowNum) -> rs.getLong(1));

        final List<Map<String, Object>> dataList = jdbcTemplate.query(sql, (rs, rowNum) -> {
            final ResultSetMetaData metaData = rs.getMetaData();
            final int columnCount = metaData.getColumnCount();
            Map<String, Object> row = new HashMap<>();
            for (int i = 1; i <= columnCount; i++) {
                row.put(metaData.getColumnName(i), rs.getObject(i));
            }
            return row;
        });
        return new PageImpl<>(dataList, PageRequest.of(page, size), Objects.nonNull(total) ? total : dataList.size());
    }


}
