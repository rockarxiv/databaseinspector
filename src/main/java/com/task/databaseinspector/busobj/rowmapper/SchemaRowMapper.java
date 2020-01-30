package com.task.databaseinspector.busobj.rowmapper;

import com.task.databaseinspector.busobj.dto.SchemaDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.task.databaseinspector.util.ColumnNames.*;

public class SchemaRowMapper implements RowMapper<SchemaDto> {
    @Override
    public SchemaDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        SchemaDto schemaDto = new SchemaDto();
        schemaDto.setCatalog(rs.getString(CATALOG_NAME));
        schemaDto.setName(rs.getString(SCHEMA_NAME));
        schemaDto.setOwner(rs.getString(SCHEMA_OWNER));
        schemaDto.setDefaultCharacterSetCatalog(rs.getString(DEFAULT_CHARACTER_SET_CATALOG));
        schemaDto.setDefaultCharacterSetSchema(rs.getString(DEFAULT_CHARACTER_SET_SCHEMA));
        schemaDto.setDefaultCharacterSetName(rs.getString(DEFAULT_CHARACTER_SET_NAME));
        schemaDto.setSqlPath(rs.getString(SQL_PATH));
        return schemaDto;
    }
}
