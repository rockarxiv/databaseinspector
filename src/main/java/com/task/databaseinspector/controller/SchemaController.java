package com.task.databaseinspector.controller;

import com.task.databaseinspector.busobj.dto.SchemaDto;
import com.task.databaseinspector.service.SchemaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schema")
@Slf4j
public class SchemaController {

    private final SchemaService schemaService;

    public SchemaController(SchemaService schemaService) {
        this.schemaService = schemaService;
    }


    @GetMapping
    public ResponseEntity<Page<SchemaDto>> getAll(@RequestParam(name = "size", defaultValue = "100") int size,
                                                  @RequestParam(name = "page", defaultValue = "0") int page) {
        log.info("Received Schemas.getAll request with page={}, size={}", page, size);
        final ResponseEntity<Page<SchemaDto>> schemas = ResponseEntity.ok(schemaService.getAll(page, size));
        log.debug("Result for Schemas.getAll request\nschemas={}", schemas);
        return schemas;
    }
}
