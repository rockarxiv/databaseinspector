package com.task.databaseinspector.busobj.entity.routing;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@ToString
@EqualsAndHashCode
public class TableKey implements Serializable {

    private String tableName;
    @Column(name = "table_schema")
    private String schemaName;
}
