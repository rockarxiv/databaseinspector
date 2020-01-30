package com.task.databaseinspector.controller;

import com.task.databaseinspector.busobj.dto.ConnectionDto;
import com.task.databaseinspector.service.ConnectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RequestMapping("/connection")
@RestController
@Validated
@Slf4j
public class ConnectionController {

    private final ConnectionService connectionService;

    public ConnectionController(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @GetMapping
    public ResponseEntity<Page<ConnectionDto>> getAll(@RequestParam(name = "size", defaultValue = "100") int size,
                                                      @RequestParam(name = "page", defaultValue = "0") int page) {
        log.info("Received Connections.getAll request page={}, size={}", page, size);
        Page<ConnectionDto> connections = connectionService.getAll(page, size);
        log.debug("Result for Connections.getAll request\nconnections={}", connections);
        return ResponseEntity.ok(connections);
    }

    @PostMapping
    public ResponseEntity<ConnectionDto> create(@Validated(ConnectionDto.New.class) ConnectionDto connectionDto) {
        log.info("Received Connection.create with connection details\n{}", connectionDto);
        return ResponseEntity.ok(connectionService.create(connectionDto));
    }

    @PutMapping
    public ResponseEntity<ConnectionDto> update(@Validated(ConnectionDto.Update.class) ConnectionDto connectionDto) {
        log.info("Received Connection.update with connection details\n{}", connectionDto);
        return ResponseEntity.ok(connectionService.update(connectionDto));
    }

    @DeleteMapping
    public void delete(@RequestParam @NotNull Long id) {
        log.info("Received Connections.delete request with id={}", id);
        connectionService.delete(id);
        log.info("Deleted Connection with id={}", id);
    }

}
