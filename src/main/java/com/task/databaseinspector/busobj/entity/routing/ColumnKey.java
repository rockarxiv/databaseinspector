package com.task.databaseinspector.busobj.entity.routing;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@ToString
@EqualsAndHashCode
public class ColumnKey implements Serializable {
    private String tableSchema;
    private String tableName;
    private String columnName;
}
