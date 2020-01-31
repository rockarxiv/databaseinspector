package com.task.databaseinspector.busobj.entity.routing;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name = "schemata", schema = "information_schema")
public class SchemaEntity {

    @Id
    @Column(name = "schema_name")
    private String name;
    @Column(name = "catalog_name")
    private String catalog;
    @Column(name = "schema_owner")
    private String owner;
    private String defaultCharacterSetCatalog;
    private String defaultCharacterSetSchema;
    private String defaultCharacterSetName;
    private String sqlPath;

}
