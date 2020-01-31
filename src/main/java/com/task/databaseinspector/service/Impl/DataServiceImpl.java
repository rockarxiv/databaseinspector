package com.task.databaseinspector.service.Impl;

import com.task.databaseinspector.dao.routing.DataDao;
import com.task.databaseinspector.service.DataService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DataServiceImpl implements DataService {

    private final DataDao dataDao;

    public DataServiceImpl(DataDao dataDao) {
        this.dataDao = dataDao;
    }

    @Override
    public Page<Map<String, Object>> getData(String schema, String tableName, int page, int size) {
        return dataDao.getData(schema, tableName, page, size);
    }
}
