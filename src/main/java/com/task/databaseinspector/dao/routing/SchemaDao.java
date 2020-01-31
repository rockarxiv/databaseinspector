package com.task.databaseinspector.dao.routing;

import com.task.databaseinspector.busobj.entity.routing.SchemaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface SchemaDao extends Repository<SchemaEntity, String> {
    @Query("SELECT s from SchemaEntity s where s.name NOT IN ('pg_catalog', 'information_schema')")
    Page<SchemaEntity> findAll(Pageable pageable);
}
