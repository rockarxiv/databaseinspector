package com.task.databaseinspector.busobj.entity.routing;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "key_column_usage", schema = "information_schema")
public class ConstraintEntity {
    @EmbeddedId
    private ConstraintKey key;
    private String constraintCatalog;


    @ManyToOne
    @MapsId("key")
    @JoinColumns({
            @JoinColumn(name = "tableSchema", referencedColumnName = "tableSchema"),
            @JoinColumn(name = "tableName", referencedColumnName = "tableName"),
            @JoinColumn(name = "constraintName", referencedColumnName = "constraintName")
    })
    private ConstraintDetailsEntity details;

}
