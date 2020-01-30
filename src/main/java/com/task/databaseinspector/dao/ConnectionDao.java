package com.task.databaseinspector.dao;

import com.task.databaseinspector.busobj.entity.ConnectionEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ConnectionDao extends PagingAndSortingRepository<ConnectionEntity, Long> {
}
