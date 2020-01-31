package com.task.databaseinspector.dao.routing;

import com.task.databaseinspector.busobj.entity.routing.TableEntity;
import com.task.databaseinspector.busobj.entity.routing.TableKey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface TableDao extends Repository<TableEntity, TableKey> {

    @Query("select t from TableEntity t where (:schema is null or t.tableKey.schemaName = :schema) and t.tableKey.schemaName NOT IN ('pg_catalog', 'information_schema')")
    Page<TableEntity> findAllBySchemaName(@Param("schema") String schema, Pageable pageable);

}
