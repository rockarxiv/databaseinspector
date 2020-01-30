package com.task.databaseinspector.dao;

import com.task.databaseinspector.busobj.dto.SchemaDto;
import org.springframework.data.domain.Page;

public interface SchemaDao {
    Page<SchemaDto> getAll(Long connectionId, int page, int size);
}
