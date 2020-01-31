package com.task.databaseinspector.busobj.dto;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class TableStatistics {
    private String tableName;
    private String schemaName;
    private long recordsCount;
    private long attributesCount;

    public TableStatistics(String schemaName, String tableName, long attributesCount) {
        this.tableName = tableName;
        this.schemaName = schemaName;
        this.attributesCount = attributesCount;
    }
}
