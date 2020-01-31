package com.task.databaseinspector.controller;

import com.task.databaseinspector.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.Map;

@RequestMapping("/data")
@RestController
@Validated
@Slf4j
public class DataController {

    private final DataService dataService;

    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping
    public ResponseEntity<Page<Map<String, Object>>> getData(@RequestParam @NotNull(message = "{schema.param.null}") String schema,
                                                             @RequestParam @NotNull(message = "{table.param.null}") String tableName,
                                                             @RequestParam(name = "size", defaultValue = "100") int size,
                                                             @RequestParam(name = "page", defaultValue = "0") int page) {
        log.info("Received DataController.getData request schema={} tableName={} page={}, size={}", schema, tableName, page, size);
        final ResponseEntity<Page<Map<String, Object>>> data = ResponseEntity.ok(dataService.getData(schema, tableName, page, size));
        log.debug("Result for DataController.getData request\ndata={}", data);
        return data;
    }

}
