package com.task.databaseinspector.dao.routing.Impl;

import com.task.databaseinspector.busobj.dto.ColumnDto;
import com.task.databaseinspector.busobj.dto.ColumnStatistic;
import com.task.databaseinspector.busobj.dto.TableDto;
import com.task.databaseinspector.busobj.dto.TableStatistics;
import com.task.databaseinspector.dao.routing.StatisticsDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Repository
public class StatisticsDaoImpl implements StatisticsDao {

    private final JdbcTemplate jdbcTemplate;

    public StatisticsDaoImpl(@Qualifier("routingDataSource") DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Page<ColumnStatistic> getColumnsStatistics(String schema, String tableName, Page<ColumnDto> columnNames) {
        final String selectColumns = columnNames.stream().map(ColumnDto::getColumnName).collect(Collector.of(() -> new StringJoiner(", ", "", ""),
                (stringJoiner, o) -> {
                    stringJoiner.add("avg(" + o + ") as " + o + "_avg").add("min(" + o + ") as " + o + "_min")
                            .add("max(" + o + ") as " + o + "_max")
                    //.add("median(" + o + ") as " + o + "_median")
                    ;
                }, StringJoiner::merge,
                StringJoiner::toString));
        String sql = "SELECT " + selectColumns + " FROM " + schema + "." + tableName;
        final List<ColumnStatistic> result = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            List<ColumnStatistic> columnStatistics = new ArrayList<>();
            for (ColumnDto column : columnNames) {
                ColumnStatistic columnStatistic = new ColumnStatistic();
                final String columnName = column.getColumnName();
                columnStatistic.setColumnName(columnName);
                columnStatistic.setTableName(column.getTableName());
                columnStatistic.setTableSchema(column.getTableSchema());
                columnStatistic.setAvg(rs.getObject(columnName + "_avg"));
                columnStatistic.setMax(rs.getObject(columnName + "_max"));
                columnStatistic.setMin(rs.getObject(columnName + "_min"));
//                    columnStatistic.setMin(rs.getObject(columnName + "_median")); There is no median function for Postgres ((
                columnStatistics.add(columnStatistic);
            }
            return columnStatistics;
        });
        if (result != null) {
            return new PageImpl<>(result, columnNames.getPageable(), columnNames.getTotalElements());
        } else {
            return Page.empty();
        }
    }

    @Override
    public Page<TableStatistics> getTablesRowsCount(String schema, Page<TableStatistics> columnsCountForTablesFromSchema) {
        final String tableNames = columnsCountForTablesFromSchema.stream().map(TableStatistics::getTableName).map(s -> "'" + s + "'").collect(Collectors.joining(", ", "", ""));
        final Map<String, TableStatistics> tableStaticsMap = columnsCountForTablesFromSchema.stream().collect(Collectors.toMap(TableStatistics::getTableName, Function.identity()));
        String sql = "SELECT relname,n_live_tup \n" +
                "  FROM pg_stat_user_tables \n" +
                " where schemaname='" + schema +"' AND relname IN ("+ tableNames +")";
        jdbcTemplate.query(sql, rs -> {
            tableStaticsMap.get(rs.getString("relname")).setRecordsCount(rs.getLong("n_live_tup"));
        });
        return columnsCountForTablesFromSchema;
    }

}
