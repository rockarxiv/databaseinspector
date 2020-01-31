package com.task.databaseinspector.service.Impl;

import com.task.databaseinspector.busobj.dto.TableDto;
import com.task.databaseinspector.dao.routing.TableDao;
import com.task.databaseinspector.mapping.TableMapper;
import com.task.databaseinspector.service.TableService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TableServiceImpl implements TableService {


    private final TableDao dao;

    private final TableMapper mapper;

    public TableServiceImpl(TableDao dao, TableMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    public Page<TableDto> getAll(String schema, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return dao.findAllBySchemaName(schema, pageable).map(mapper::toDto);
    }
}
