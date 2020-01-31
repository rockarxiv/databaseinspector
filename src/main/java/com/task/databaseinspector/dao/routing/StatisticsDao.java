package com.task.databaseinspector.dao.routing;

import com.task.databaseinspector.busobj.dto.ColumnDto;
import com.task.databaseinspector.busobj.dto.ColumnStatistic;
import com.task.databaseinspector.busobj.dto.TableDto;
import com.task.databaseinspector.busobj.dto.TableStatistics;
import org.springframework.data.domain.Page;

public interface StatisticsDao {
    Page<ColumnStatistic> getColumnsStatistics(String schema, String tableName, Page<ColumnDto> columnNames);

    Page<TableStatistics> getTablesRowsCount(String schema, Page<TableStatistics> columnsCountForTablesFromSchema);
}
