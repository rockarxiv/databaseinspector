package com.task.databaseinspector.busobj.entity.routing;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "columns", schema = "information_schema")
public class ColumnEntity {
    @EmbeddedId
    private ColumnKey columnKey;
    private String tableCatalog;
    private Integer ordinalPosition;
    private String columnDefault;
    private String isNullable;
    private String dataType;
    private Integer characterMaximumLength;
    private Integer characterOctetLength;
    private Integer numericPrecision;
    private Integer numericPrecisionRadix;
    private Integer numericScale;
    private Integer datetimePrecision;
    private String intervalType;
    private Integer intervalPrecision;
    private String characterSetCatalog;
    private String characterSetSchema;
    private String characterSetName;
    private String collationCatalog;
    private String collationSchema;
    private String collationName;
    private String domainCatalog;
    private String domainSchema;
    private String domainName;
    private String udtCatalog;
    private String udtSchema;
    private String udtName;
    private String scopeCatalog;
    private String scopeSchema;
    private String scopeName;
    private Integer maximumCardinality;
    private String dtdIdentifier;
    private String isSelfReferencing;
    private String isIdentity;
    private String identityGeneration;
    private String identityStart;
    private String identityIncrement;
    private String identityMaximum;
    private String identityMinimum;
    private String identityCycle;
    private String isGenerated;
    private String generationExpression;
    private String isUpdatable;

    @OneToMany
    @MapsId("key")
    @JoinColumns({
            @JoinColumn(name = "tableSchema", referencedColumnName = "tableSchema"),
            @JoinColumn(name = "tableName", referencedColumnName = "tableName"),
            @JoinColumn(name = "columnName", referencedColumnName = "columnName")
    })
    private List<ConstraintEntity> constraints;
}
