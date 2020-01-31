package com.task.databaseinspector.busobj.dto;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ColumnStatistic {
    private String tableSchema;
    private String tableName;
    private String columnName;
    private Object avg;
    private Object max;
    private Object min;
    private Object median;

}
