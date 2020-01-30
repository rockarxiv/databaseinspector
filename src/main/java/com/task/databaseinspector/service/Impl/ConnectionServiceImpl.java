package com.task.databaseinspector.service.Impl;

import com.task.databaseinspector.busobj.dto.ConnectionDto;
import com.task.databaseinspector.busobj.entity.ConnectionEntity;
import com.task.databaseinspector.cache.JdbcTemplateCache;
import com.task.databaseinspector.dao.ConnectionDao;
import com.task.databaseinspector.exception.ConnectionNotFoundException;
import com.task.databaseinspector.mapping.ConnectionMapper;
import com.task.databaseinspector.service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConnectionServiceImpl implements ConnectionService {


    private final ConnectionDao connectionDao;
    private final ConnectionMapper connectionMapper;
    private final JdbcTemplateCache jdbcTemplateCache;

    @Autowired
    public ConnectionServiceImpl(ConnectionDao connectionDao, @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") ConnectionMapper connectionMapper, JdbcTemplateCache jdbcTemplateCache) {
        this.connectionDao = connectionDao;
        this.connectionMapper = connectionMapper;
        this.jdbcTemplateCache = jdbcTemplateCache;
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
        ConnectionDto result = null;
        if(optionalEntity.isPresent()) {
            ConnectionEntity entity = optionalEntity.get();
            connectionMapper.update(entity, connectionDto);
            result = connectionMapper.toDto(connectionDao.save(entity));
            jdbcTemplateCache.removeFromCache(connectionDto.getId());
        } else {
            throw new ConnectionNotFoundException(connectionDto.getId());
        }
        return result;
    }

    @Override
    public Boolean delete(Long id) {
        connectionDao.deleteById(id);
        jdbcTemplateCache.removeFromCache(id);
        return true;
    }
}
