package com.task.databaseinspector.controller;

import com.task.databaseinspector.busobj.dto.ColumnDto;
import com.task.databaseinspector.service.ColumnService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/column")
@Slf4j
@Validated
public class ColumnController {

    private final ColumnService columnService;

    public ColumnController(ColumnService columnService) {
        this.columnService = columnService;
    }

    @GetMapping
    public ResponseEntity<Page<ColumnDto>> getAll(@RequestParam @NotNull(message = "{schema.param.null}") String schema,
                                                  @RequestParam @NotNull(message = "{table.param.null}") String tableName,
                                                  @RequestParam(name = "size", defaultValue = "100") int size,
                                                  @RequestParam(name = "page", defaultValue = "0") int page) {
        log.info("Received Columns.getAll request with schema={} tableName={} page={}, size={}",schema, tableName, page, size);
        final ResponseEntity<Page<ColumnDto>> columnDtos = ResponseEntity.ok(columnService.getAll(schema, tableName, page, size));
        log.debug("Result for Columns.getAll request\ncolumns={}", columnDtos);
        return columnDtos;
    }
}
