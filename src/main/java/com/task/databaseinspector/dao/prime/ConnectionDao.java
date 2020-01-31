package com.task.databaseinspector.dao.prime;

import com.task.databaseinspector.busobj.entity.prime.ConnectionEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ConnectionDao extends PagingAndSortingRepository<ConnectionEntity, Long> {
}
