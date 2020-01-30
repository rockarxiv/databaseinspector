package com.task.databaseinspector.service.Impl;

import com.task.databaseinspector.busobj.dto.SchemaDto;
import com.task.databaseinspector.dao.SchemaDao;
import com.task.databaseinspector.service.SchemaService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class SchemaServiceImpl implements SchemaService {

    private final SchemaDao schemaDao;

    public SchemaServiceImpl(SchemaDao schemaDao) {
        this.schemaDao = schemaDao;
    }


    @Override
    public Page<SchemaDto> getAll(Long connectionId, int page, int size) {
        return schemaDao.getAll(connectionId, page, size);
    }
}
