package com.task.databaseinspector.service.Impl;

import com.task.databaseinspector.busobj.dto.TableDto;
import com.task.databaseinspector.dao.TableDao;
import com.task.databaseinspector.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class TableServiceImpl implements TableService {

    @Autowired
    private TableDao tableDao;

    @Override
    public Page<TableDto> getAll(Long connectionId, String schema, int page, int size) {
        return tableDao.getAll(connectionId, schema, page, size);
    }
}
