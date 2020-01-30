package com.task.databaseinspector.service;

import com.task.databaseinspector.busobj.dto.TableDto;
import org.springframework.data.domain.Page;

public interface TableService {

    Page<TableDto> getAll(Long connectionId, String schema, int page, int size);
}
