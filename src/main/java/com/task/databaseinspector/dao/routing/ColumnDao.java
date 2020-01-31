package com.task.databaseinspector.dao.routing;

import com.task.databaseinspector.busobj.dto.TableStatistics;
import com.task.databaseinspector.busobj.entity.routing.ColumnEntity;
import com.task.databaseinspector.busobj.entity.routing.ColumnKey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface ColumnDao extends Repository<ColumnEntity, ColumnKey> {
    @Query("select t from ColumnEntity t where (:schema is null or t.columnKey.tableSchema = :schema) and (:tableName is null or t.columnKey.tableName = :tableName) and t.columnKey.tableSchema NOT IN ('pg_catalog', 'information_schema')")
    Page<ColumnEntity> findAll(String schema, String tableName, Pageable pageable);

    @Query("select t from ColumnEntity t where (:schema is null or t.columnKey.tableSchema = :schema) and (:tableName is null or t.columnKey.tableName = :tableName) and t.columnKey.tableSchema NOT IN ('pg_catalog', 'information_schema') and t.numericPrecisionRadix is not null")
    Page<ColumnEntity> findAllNumericColumns(String schema, String tableName, Pageable pageable);

    @Query("select new com.task.databaseinspector.busobj.dto.TableStatistics(t.columnKey.tableSchema, t.columnKey.tableName, count(t.columnKey.columnName)) from ColumnEntity t where t.columnKey.tableSchema = :schema and t.columnKey.tableSchema NOT IN ('pg_catalog', 'information_schema') group by t.columnKey.tableSchema, t.columnKey.tableName")
    Page<TableStatistics> getColumnsCountForTablesFromSchema(String schema, Pageable pageable);
}
