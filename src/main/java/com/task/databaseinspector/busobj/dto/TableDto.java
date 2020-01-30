package com.task.databaseinspector.busobj.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TableDto {
    private String tableName;
    private String schemaName;
    private String tableType;
    private String catalog;
    private String selfReferencingColumnName;
    private String referenceGeneration;
    private String userDefinedTypeCatalog;
    private String userDefinedTypeSchema;
    private String userDefinedTypeName;
    private String isInsertableInto;
    private String isTyped;
    private String commitAction;
}
