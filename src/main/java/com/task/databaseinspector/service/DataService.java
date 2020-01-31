package com.task.databaseinspector.service;

import org.springframework.data.domain.Page;

import java.util.Map;

public interface DataService {
    Page<Map<String, Object>> getData(String schema, String tableName, int page, int size);
}
