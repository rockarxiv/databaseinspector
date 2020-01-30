package com.task.databaseinspector.service;

import com.task.databaseinspector.busobj.dto.ColumnDto;
import org.springframework.data.domain.Page;

public interface ColumnService {
    Page<ColumnDto> getAll(Long connectionId, String schema, String tableName, int page, int size);
}
