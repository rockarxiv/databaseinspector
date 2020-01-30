package com.task.databaseinspector.dao.Impl;

import com.task.databaseinspector.busobj.dto.SchemaDto;
import com.task.databaseinspector.busobj.rowmapper.SchemaRowMapper;
import com.task.databaseinspector.cache.JdbcTemplateCache;
import com.task.databaseinspector.dao.SchemaDao;
import com.task.databaseinspector.util.SelectQueryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

import static com.task.databaseinspector.util.ColumnNames.*;

@Repository
@Slf4j
public class SchemaDaoImpl implements SchemaDao {

    private static final String SCHEMATA_TABLE = "schemata";
    private static final SchemaRowMapper SCHEMA_ROW_MAPPER = new SchemaRowMapper();


    private final JdbcTemplateCache jdbcTemplateCache;

    public SchemaDaoImpl(JdbcTemplateCache jdbcTemplateCache) {
        this.jdbcTemplateCache = jdbcTemplateCache;
    }

    @Override
    public Page<SchemaDto> getAll(Long connectionId, int page, int size) {
        JdbcTemplate jdbcTemplate = jdbcTemplateCache.getJdbcTemplate(connectionId);
        if (jdbcTemplate != null) {
            SelectQueryBuilder queryBuilder = new SelectQueryBuilder();
            queryBuilder.withTable(INFORMATION_SCHEMA, SCHEMATA_TABLE)
                    .addColumns(CATALOG_NAME, SCHEMA_NAME, SCHEMA_OWNER, DEFAULT_CHARACTER_SET_CATALOG,
                            DEFAULT_CHARACTER_SET_SCHEMA, DEFAULT_CHARACTER_SET_NAME, SQL_PATH)
                    .withSize(size).withPage(page);
            Long total = jdbcTemplate.queryForObject(queryBuilder.buildCountQuery(),
                    (rs, rowNum) -> rs.getLong(1));

            List<SchemaDto> schemaDtos = jdbcTemplate.query(queryBuilder.build(), SCHEMA_ROW_MAPPER);
            return new PageImpl<>(schemaDtos, PageRequest.of(page, size), Objects.nonNull(total) ? total : schemaDtos.size());
        }
        return Page.empty();
    }
}
