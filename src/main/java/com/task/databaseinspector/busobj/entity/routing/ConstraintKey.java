package com.task.databaseinspector.busobj.entity.routing;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@ToString(callSuper = true)
public class ConstraintKey extends ColumnKey {
    private String constraintSchema;
    private String constraintName;
}
