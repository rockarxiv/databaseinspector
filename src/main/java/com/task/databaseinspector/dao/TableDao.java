package com.task.databaseinspector.dao;

import com.task.databaseinspector.busobj.dto.TableDto;
import org.springframework.data.domain.Page;


public interface TableDao {
    Page<TableDto> getAll(Long connectionId, String schema, int page, int size);
}
