package com.task.databaseinspector.busobj.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ConstraintDto {
    private String constraintCatalog;
    private String constraintSchema;
    private String constraintName;
    private String constraintType;
}
