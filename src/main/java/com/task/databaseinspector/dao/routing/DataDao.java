package com.task.databaseinspector.dao.routing;

import org.springframework.data.domain.Page;

import java.util.Map;

public interface DataDao {
    Page<Map<String, Object>> getData(String schema, String tableName, int page, int size);
}
