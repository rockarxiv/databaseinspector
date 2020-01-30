package com.task.databaseinspector.mapping;

import com.task.databaseinspector.busobj.dto.ConnectionDto;
import com.task.databaseinspector.busobj.entity.ConnectionEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ConnectionMapper extends DefaultMapper<ConnectionEntity, ConnectionDto> {

    void update(@MappingTarget ConnectionEntity entity, ConnectionDto connectionDto);
}
