package com.task.databaseinspector.service;

import com.task.databaseinspector.busobj.dto.ColumnStatistic;
import com.task.databaseinspector.busobj.dto.TableStatistics;
import org.springframework.data.domain.Page;

public interface StatisticsService {
    Page<ColumnStatistic> getColumnsStatistics(String schema, String tableName, int page, int size);

    Page<TableStatistics> getTableStatistics(String schema, int page, int size);
}
