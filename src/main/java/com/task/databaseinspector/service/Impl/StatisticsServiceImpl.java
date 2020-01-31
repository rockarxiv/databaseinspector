package com.task.databaseinspector.service.Impl;

import com.task.databaseinspector.busobj.dto.ColumnDto;
import com.task.databaseinspector.busobj.dto.ColumnStatistic;
import com.task.databaseinspector.busobj.dto.TableStatistics;
import com.task.databaseinspector.dao.routing.StatisticsDao;
import com.task.databaseinspector.service.ColumnService;
import com.task.databaseinspector.service.StatisticsService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private final ColumnService columnService;
    private final StatisticsDao statisticsDao;

    public StatisticsServiceImpl(ColumnService columnService, StatisticsDao statisticsDao) {
        this.columnService = columnService;
        this.statisticsDao = statisticsDao;
    }

    @Override
    public Page<ColumnStatistic> getColumnsStatistics(String schema, String tableName, int page, int size) {
        final Page<ColumnDto> columnNames = columnService.getAllNumericColumns(schema, tableName, page, size);
        return statisticsDao.getColumnsStatistics(schema, tableName, columnNames);
    }

    @Override
    public Page<TableStatistics> getTableStatistics(String schema, int page, int size) {
        final Page<TableStatistics> columnsCountForTablesFromSchema = columnService.getColumnsCountForTablesFromSchema(schema, page, size);
        return statisticsDao.getTablesRowsCount(schema, columnsCountForTablesFromSchema);
    }
}
