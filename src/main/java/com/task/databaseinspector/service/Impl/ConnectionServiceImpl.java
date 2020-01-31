package com.task.databaseinspector.service.Impl;

import com.task.databaseinspector.busobj.dto.ConnectionDto;
import com.task.databaseinspector.busobj.entity.prime.ConnectionEntity;
import com.task.databaseinspector.dao.prime.ConnectionDao;
import com.task.databaseinspector.datasource.DataSourceCache;
import com.task.databaseinspector.exception.ConnectionNotFoundException;
import com.task.databaseinspector.mapping.ConnectionMapper;
import com.task.databaseinspector.service.ConnectionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConnectionServiceImpl implements ConnectionService {


    private final ConnectionDao connectionDao;
    private final ConnectionMapper connectionMapper;
    private final DataSourceCache dataSourceCache;


    public ConnectionServiceImpl(ConnectionDao connectionDao, @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") ConnectionMapper connectionMapper, DataSourceCache dataSourceCache) {
        this.connectionDao = connectionDao;
        this.connectionMapper = connectionMapper;
        this.dataSourceCache = dataSourceCache;
    }

    @Override
    public ConnectionDto get(Long id) {
        return connectionMapper.toDto(connectionDao.findById(id).orElseThrow(() -> new ConnectionNotFoundException(id)));
    }

    @Override
    public Page<ConnectionDto> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return connectionDao.findAll(pageable).map(connectionMapper::toDto);
    }

    @Override
    public ConnectionDto create(ConnectionDto connectionDto) {
        return connectionMapper.toDto(connectionDao.save(connectionMapper.toEntity(connectionDto)));
    }

    @Override
    public ConnectionDto update(ConnectionDto connectionDto) {
        Optional<ConnectionEntity> optionalEntity = connectionDao.findById(connectionDto.getId());
        ConnectionDto result;
        if(optionalEntity.isPresent()) {
            ConnectionEntity entity = optionalEntity.get();
            connectionMapper.update(entity, connectionDto);
            result = connectionMapper.toDto(connectionDao.save(entity));
            dataSourceCache.removeFromCache(connectionDto.getId());
            return result;
        } else {
            throw new ConnectionNotFoundException(connectionDto.getId());
        }
    }

    @Override
    public void delete(Long id) {
        connectionDao.deleteById(id);
        dataSourceCache.removeFromCache(id);
    }

}
