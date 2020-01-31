package com.task.databaseinspector.busobj.entity.routing;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tables", schema = "information_schema")
public class TableEntity {
    @EmbeddedId
    private TableKey tableKey;
    private String tableType;
    @Column(name = "table_catalog")
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
