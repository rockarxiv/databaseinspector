package com.task.databaseinspector.service;

import com.task.databaseinspector.busobj.dto.ColumnDto;
import com.task.databaseinspector.busobj.dto.TableStatistics;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ColumnService {

    Page<ColumnDto> getAll(String schema, String tableName, int page, int size);

    Page<ColumnDto> getAllNumericColumns(String schema, String tableName, int page, int size);

    Page<TableStatistics> getColumnsCountForTablesFromSchema(String schema, int page, int size);
}
