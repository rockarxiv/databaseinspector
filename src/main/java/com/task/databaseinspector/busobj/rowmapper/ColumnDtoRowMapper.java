package com.task.databaseinspector.busobj.rowmapper;

import com.task.databaseinspector.busobj.dto.ColumnDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.task.databaseinspector.util.ColumnNames.*;

public class ColumnDtoRowMapper implements RowMapper<ColumnDto> {
    @Override
    public ColumnDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        ColumnDto columnDto = new ColumnDto();
        columnDto.setTableCatalog(rs.getString(TABLE_CATALOG));
        columnDto.setTableSchema(rs.getString(TABLE_SCHEMA));
        columnDto.setTableName(rs.getString(TABLE_NAME));
        columnDto.setColumnName(rs.getString(COLUMN_NAME));
        columnDto.setOrdinalPosition(rs.getInt(ORDINAL_POSITION));
        columnDto.setColumnDefault(rs.getString(COLUMN_DEFAULT));
        columnDto.setIsNullable(rs.getString(IS_NULLABLE));
        columnDto.setDataType(rs.getString(DATA_TYPE));
        columnDto.setCharacterMaximumLength(rs.getInt(CHARACTER_MAXIMUM_LENGTH));
        columnDto.setCharacterOctetLength(rs.getInt(CHARACTER_OCTET_LENGTH));
        columnDto.setNumericPrecision(rs.getInt(NUMERIC_PRECISION));
        columnDto.setNumericPrecisionRadix(rs.getInt(NUMERIC_PRECISION_RADIX));
        columnDto.setNumericScale(rs.getInt(NUMERIC_SCALE));
        columnDto.setDatetimePrecision(rs.getInt(DATETIME_PRECISION));
        columnDto.setIntervalType(rs.getString(INTERVAL_TYPE));
        columnDto.setIntervalPrecision(rs.getInt(INTERVAL_PRECISION));
        columnDto.setCharacterSetCatalog(rs.getString(CHARACTER_SET_CATALOG));
        columnDto.setCharacterSetSchema(rs.getString(CHARACTER_SET_SCHEMA));
        columnDto.setCharacterSetName(rs.getString(CHARACTER_SET_NAME));
        columnDto.setCollationCatalog(rs.getString(COLLATION_CATALOG));
        columnDto.setCollationSchema(rs.getString(COLLATION_SCHEMA));
        columnDto.setCollationName(rs.getString(COLLATION_NAME));
        columnDto.setDomainCatalog(rs.getString(DOMAIN_CATALOG));
        columnDto.setDomainSchema(rs.getString(DOMAIN_SCHEMA));
        columnDto.setDomainName(rs.getString(DOMAIN_NAME));
        columnDto.setUdtCatalog(rs.getString(UDT_CATALOG));
        columnDto.setUdtSchema(rs.getString(UDT_SCHEMA));
        columnDto.setUdtName(rs.getString(UDT_NAME));
        columnDto.setScopeCatalog(rs.getString(SCOPE_CATALOG));
        columnDto.setScopeSchema(rs.getString(SCOPE_SCHEMA));
        columnDto.setScopeName(rs.getString(SCOPE_NAME));
        columnDto.setMaximumCardinality(rs.getInt(MAXIMUM_CARDINALITY));
        columnDto.setDtdIdentifier(rs.getString(DTD_IDENTIFIER));
        columnDto.setIsSelfReferencing(rs.getString(IS_SELF_REFERENCING));
        columnDto.setIsIdentity(rs.getString(IS_IDENTITY));
        columnDto.setIdentityGeneration(rs.getString(IDENTITY_GENERATION));
        columnDto.setIdentityStart(rs.getString(IDENTITY_START));
        columnDto.setIdentityIncrement(rs.getString(IDENTITY_INCREMENT));
        columnDto.setIdentityMaximum(rs.getString(IDENTITY_MAXIMUM));
        columnDto.setIdentityMinimum(rs.getString(IDENTITY_MINIMUM));
        columnDto.setIdentityCycle(rs.getString(IDENTITY_CYCLE));
        columnDto.setIsGenerated(rs.getString(IS_GENERATED));
        columnDto.setGenerationExpression(rs.getString(GENERATION_EXPRESSION));
        columnDto.setIsUpdatable(rs.getString(IS_UPDATABLE));
        columnDto.setConstraintCatalog(rs.getString(CONSTRAINT_CATALOG));
        columnDto.setConstraintName(rs.getString(CONSTRAINT_NAME));
        columnDto.setConstraintSchema(rs.getString(CONSTRAINT_SCHEMA));
        return columnDto;
    }
}
