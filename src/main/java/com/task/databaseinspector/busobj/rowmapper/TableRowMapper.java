package com.task.databaseinspector.busobj.rowmapper;

import com.task.databaseinspector.busobj.dto.TableDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.task.databaseinspector.util.ColumnNames.*;
import static com.task.databaseinspector.util.ColumnNames.COMMIT_ACTION;

public class TableRowMapper implements RowMapper<TableDto> {
    @Override
    public TableDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        TableDto tableDto = new TableDto();
        tableDto.setCatalog(rs.getString(TABLE_CATALOG));
        tableDto.setSchemaName(rs.getString(TABLE_SCHEMA));
        tableDto.setTableName(rs.getString(TABLE_NAME));
        tableDto.setTableType(rs.getString(TABLE_TYPE));
        tableDto.setSelfReferencingColumnName(rs.getString(SELF_REFERENCING_COLUMN_NAME));
        tableDto.setReferenceGeneration(rs.getString(REFERENCE_GENERATION));
        tableDto.setUserDefinedTypeCatalog(rs.getString(USER_DEFINED_TYPE_CATALOG));
        tableDto.setUserDefinedTypeSchema(rs.getString(USER_DEFINED_TYPE_SCHEMA));
        tableDto.setUserDefinedTypeName(rs.getString(USER_DEFINED_TYPE_NAME));
        tableDto.setIsInsertableInto(rs.getString(IS_INSERTABLE_INTO));
        tableDto.setIsTyped(rs.getString(IS_TYPED));
        tableDto.setCommitAction(rs.getString(COMMIT_ACTION));
        return tableDto;
    }
}
