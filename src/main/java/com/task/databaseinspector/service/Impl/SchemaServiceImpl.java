package com.task.databaseinspector.service.Impl;

import com.task.databaseinspector.busobj.dto.SchemaDto;
import com.task.databaseinspector.dao.routing.SchemaDao;
import com.task.databaseinspector.mapping.SchemaMapper;
import com.task.databaseinspector.service.SchemaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class SchemaServiceImpl implements SchemaService {

    private final SchemaDao schemaDao;
    private final SchemaMapper mapper;

    public SchemaServiceImpl(SchemaDao schemaDao, SchemaMapper mapper) {
        this.schemaDao = schemaDao;
        this.mapper = mapper;
    }


    @Override
    public Page<SchemaDto> getAll(int page, int size) {
        return schemaDao.findAll(PageRequest.of(page, size)).map(mapper::toDto);
    }
}
