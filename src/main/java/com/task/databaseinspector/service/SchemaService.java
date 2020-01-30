package com.task.databaseinspector.service;

import com.task.databaseinspector.busobj.dto.SchemaDto;
import org.springframework.data.domain.Page;

public interface SchemaService {
    Page<SchemaDto> getAll(Long connectionId, int page, int size);
}
