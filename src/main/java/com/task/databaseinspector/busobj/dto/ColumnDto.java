package com.task.databaseinspector.busobj.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ColumnDto {
    private String tableCatalog;
    private String tableSchema;
    private String tableName;
    private String columnName;
    private int ordinalPosition;
    private String columnDefault;
    private String isNullable;
    private String dataType;
    private int characterMaximumLength;
    private int characterOctetLength;
    private int numericPrecision;
    private int numericPrecisionRadix;
    private int numericScale;
    private int datetimePrecision;
    private String intervalType;
    private int intervalPrecision;
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
    private int maximumCardinality;
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
    private String constraintCatalog;
    private String constraintSchema;
    private String constraintName;
}