package com.task.databaseinspector.mapping;

import com.task.databaseinspector.busobj.dto.ColumnDto;
import com.task.databaseinspector.busobj.dto.ConstraintDto;
import com.task.databaseinspector.busobj.entity.routing.ColumnEntity;
import com.task.databaseinspector.busobj.entity.routing.ConstraintEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ColumnMapper extends DefaultMapper<ColumnEntity, ColumnDto>{
    @Mapping(source = "columnKey.tableSchema", target = "tableSchema")
    @Mapping(source = "columnKey.tableName", target = "tableName")
    @Mapping(source = "columnKey.columnName", target = "columnName")
    @Mapping(source = "constraints", target = "constraintDtos")
    ColumnDto toDto(ColumnEntity entity);

    @Mapping(source = "key.constraintSchema", target = "constraintSchema")
    @Mapping(source = "key.constraintName", target = "constraintName")
    @Mapping(source = "details.constraintType", target = "constraintType")
    ConstraintDto toDto(ConstraintEntity constraintEntity);
}
