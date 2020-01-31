package com.task.databaseinspector.mapping;

import com.task.databaseinspector.busobj.dto.TableDto;
import com.task.databaseinspector.busobj.entity.routing.TableEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface TableMapper extends DefaultMapper<TableEntity, TableDto> {

    @Mapping(source = "tableKey.tableName", target = "tableName")
    @Mapping(source = "tableKey.schemaName", target = "schemaName")
    TableDto toDto(TableEntity entity);
}


