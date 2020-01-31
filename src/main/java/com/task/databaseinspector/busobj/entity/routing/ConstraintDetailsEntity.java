package com.task.databaseinspector.busobj.entity.routing;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Entity
@Table(name = "table_constraints", schema = "information_schema")
public class ConstraintDetailsEntity {
    private String constraintType;

    @EmbeddedId
    private ConstraintDetailsKey key;

    @Embeddable
    @Getter
    @Setter
    @ToString
    public static class ConstraintDetailsKey implements Serializable {
        private String tableSchema;
        private String tableName;
        private String constraintName;
    }
}
