package com.task.databaseinspector.controller;

import com.task.databaseinspector.busobj.dto.ColumnStatistic;
import com.task.databaseinspector.busobj.dto.TableStatistics;
import com.task.databaseinspector.service.StatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/statistics")
@Validated
@Slf4j
public class StatisticsController {

    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping
    @RequestMapping("/columns")
    public ResponseEntity<Page<ColumnStatistic>> getColumnsStatistics(@RequestParam @NotNull(message = "{schema.param.null}") String schema,
                                                                      @RequestParam @NotNull(message = "{table.param.null}") String tableName,
                                                                      @RequestParam(name = "size", defaultValue = "100") int size,
                                                                      @RequestParam(name = "page", defaultValue = "0") int page) {
        log.info("Received StatisticsController.getColumnsStatistics request with schema={} tableName={} page={}, size={}", schema, tableName, page, size);
        final ResponseEntity<Page<ColumnStatistic>> columnStatistics = ResponseEntity.ok(statisticsService.getColumnsStatistics(schema, tableName, page, size));
        log.debug("Result for StatisticsController.getColumnsStatistics request\ncolumnStatistics={}", columnStatistics);
        return columnStatistics;

    }

    @GetMapping
    @RequestMapping("/table")
    public ResponseEntity<Page<TableStatistics>> getTableStatistics(@RequestParam @NotNull(message = "{schema.param.null}") String schema,
                                                                    @RequestParam(name = "size", defaultValue = "100") int size,
                                                                    @RequestParam(name = "page", defaultValue = "0") int page) {
        log.info("Received StatisticsController.getTableStatistics request with schema={} page={}, size={}", schema, page, size);
        ResponseEntity<Page<TableStatistics>> tableStatistics = ResponseEntity.ok(statisticsService.getTableStatistics(schema, page, size));
        log.debug("Result for StatisticsController.getTableStatistics request\ntableStatistics={}", tableStatistics);
        return tableStatistics;
    }
}
