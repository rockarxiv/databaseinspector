package com.task.databaseinspector.service;

import com.task.databaseinspector.busobj.dto.ConnectionDto;
import org.springframework.data.domain.Page;

public interface ConnectionService {

    ConnectionDto get(Long id);

    Page<ConnectionDto> getAll(Integer page, Integer size);

    ConnectionDto create(ConnectionDto connectionDto);

    ConnectionDto update(ConnectionDto connectionDto);

    Boolean delete(Long id);
}
