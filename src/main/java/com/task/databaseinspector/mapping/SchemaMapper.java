package com.task.databaseinspector.mapping;

import com.task.databaseinspector.busobj.dto.SchemaDto;
import com.task.databaseinspector.busobj.entity.routing.SchemaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface SchemaMapper extends DefaultMapper<SchemaEntity, SchemaDto> {
}
