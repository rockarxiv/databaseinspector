package com.task.databaseinspector.controller;

import com.task.databaseinspector.busobj.dto.SchemaDto;
import com.task.databaseinspector.service.SchemaService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schema")
public class SchemaController {

    private final SchemaService schemaService;

    public SchemaController(SchemaService schemaService) {
        this.schemaService = schemaService;
    }


    @GetMapping
    public ResponseEntity<Page<SchemaDto>> getAll(@RequestParam Long connectionId,
                                                  @RequestParam(name = "size", defaultValue = "100") int size,
                                                  @RequestParam(name = "page", defaultValue = "0") int page) {
        return ResponseEntity.ok(schemaService.getAll(connectionId, page, size));
    }
}
