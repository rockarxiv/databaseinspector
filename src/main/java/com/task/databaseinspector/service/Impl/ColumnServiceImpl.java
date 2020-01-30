package com.task.databaseinspector.service.Impl;

import com.task.databaseinspector.busobj.dto.ColumnDto;
import com.task.databaseinspector.dao.ColumnDao;
import com.task.databaseinspector.service.ColumnService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ColumnServiceImpl implements ColumnService {

    private final ColumnDao columnDao;

    public ColumnServiceImpl(ColumnDao columnDao) {
        this.columnDao = columnDao;
    }

    @Override
    public Page<ColumnDto> getAll(Long connectionId, String schema, String tableName, int page, int size) {
        return columnDao.getAll(connectionId, schema, tableName, page, size);
    }
}
