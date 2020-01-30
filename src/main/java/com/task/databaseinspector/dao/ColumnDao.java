package com.task.databaseinspector.dao;

import com.task.databaseinspector.busobj.dto.ColumnDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ColumnDao {

    Page<ColumnDto> getAll(Long connectionId, String schema, String tableName, int page, int size);
}
