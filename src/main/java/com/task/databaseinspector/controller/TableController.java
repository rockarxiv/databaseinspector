package com.task.databaseinspector.controller;

import com.task.databaseinspector.busobj.dto.TableDto;
import com.task.databaseinspector.service.TableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/table")
@Slf4j
public class TableController {

    private final TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping
    public ResponseEntity<Page<TableDto>> getAll(@RequestParam(required = false) String schema,
                                                 @RequestParam(name = "size", defaultValue = "100") int size,
                                                 @RequestParam(name = "page", defaultValue = "0") int page) {
        log.info("Received Tables.getAll request with schema={} page={}, size={}",schema, page, size);
        final ResponseEntity<Page<TableDto>> tables = ResponseEntity.ok(tableService.getAll(schema, page, size));
        log.debug("Result for Tables.getAll request\ndata={}", tables);
        return tables;
    }

}
