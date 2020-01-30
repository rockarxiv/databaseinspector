package com.task.databaseinspector.controller;

import com.task.databaseinspector.busobj.dto.TableDto;
import com.task.databaseinspector.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/table")
public class TableController {

    private final TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping
    public ResponseEntity<Page<TableDto>> getAll(@RequestParam Long connectionId,
                                                 @RequestParam(required = false) String schema,
                                                 @RequestParam(name = "size", defaultValue = "100") int size,
                                                 @RequestParam(name = "page", defaultValue = "0") int page) {
        return ResponseEntity.ok(tableService.getAll(connectionId, schema, page, size));
    }

}
