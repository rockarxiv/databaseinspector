package com.task.databaseinspector.controller;

import com.task.databaseinspector.busobj.dto.ColumnDto;
import com.task.databaseinspector.service.ColumnService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/column")
public class ColumnController {

    private final ColumnService columnService;

    public ColumnController(ColumnService columnService) {
        this.columnService = columnService;
    }

    @GetMapping
    public ResponseEntity<Page<ColumnDto>> getAll(@RequestParam Long connectionId,
                                                  @RequestParam(required = false) String schema,
                                                  @RequestParam(required = false) String tableName,
                                                  @RequestParam(name = "size", defaultValue = "100") int size,
                                                  @RequestParam(name = "page", defaultValue = "0") int page) {
        return ResponseEntity.ok(columnService.getAll(connectionId, schema, tableName, page, size));
    }
}
