package com.task.databaseinspector.service.Impl;

import com.task.databaseinspector.busobj.dto.ColumnDto;
import com.task.databaseinspector.busobj.dto.TableStatistics;
import com.task.databaseinspector.dao.routing.ColumnDao;
import com.task.databaseinspector.mapping.ColumnMapper;
import com.task.databaseinspector.service.ColumnService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ColumnServiceImpl implements ColumnService {

    private final ColumnDao columnDao;
    private final ColumnMapper mapper;

    public ColumnServiceImpl(ColumnDao columnDao, ColumnMapper mapper) {
        this.columnDao = columnDao;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ColumnDto> getAll(String schema, String tableName, int page, int size) {
        return columnDao.findAll(schema, tableName, PageRequest.of(page, size)).map(mapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ColumnDto> getAllNumericColumns(String schema, String tableName, int page, int size) {
        return columnDao.findAllNumericColumns(schema, tableName, PageRequest.of(page, size)).map(mapper::toDto);
    }

    @Override
    public Page<TableStatistics> getColumnsCountForTablesFromSchema(String schema, int page, int size) {
        return columnDao.getColumnsCountForTablesFromSchema(schema, PageRequest.of(page, size));
    }
}
