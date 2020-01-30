package com.task.databaseinspector.busobj.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SchemaDto {
    private String name;
    private String catalog;
    private String owner;
    private String defaultCharacterSetCatalog;
    private String defaultCharacterSetSchema;
    private String defaultCharacterSetName;
    private String sqlPath;
}
