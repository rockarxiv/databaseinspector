package com.task.databaseinspector.dao.Impl;

import com.task.databaseinspector.busobj.dto.ColumnDto;
import com.task.databaseinspector.busobj.rowmapper.ColumnDtoRowMapper;
import com.task.databaseinspector.cache.JdbcTemplateCache;
import com.task.databaseinspector.dao.ColumnDao;
import com.task.databaseinspector.util.SelectQueryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

import static com.task.databaseinspector.util.ColumnNames.*;

@Repository
@Slf4j
public class ColumnDaoImpl implements ColumnDao {

    
    private static final String COLUMNS_TABLE = "columns";
    private static final String PK_TABLE = "key_column_usage";
    private static final ColumnDtoRowMapper COLUMN_ROW_MAPPER = new ColumnDtoRowMapper();

    private final JdbcTemplateCache jdbcTemplateCache;

    private static final String SCHEMA_FILTER = COLUMNS_TABLE + "." + TABLE_SCHEMA + "='%s'";
    private static final String TABLE_FILTER = COLUMNS_TABLE + "." + TABLE_NAME + "='%s'";


    public ColumnDaoImpl(JdbcTemplateCache jdbcTemplateCache) {
        this.jdbcTemplateCache = jdbcTemplateCache;
    }

    @Override
    public Page<ColumnDto> getAll(Long connectionId, String schema, String tableName, int page, int size) {
        JdbcTemplate jdbcTemplate = jdbcTemplateCache.getJdbcTemplate(connectionId);
        if (jdbcTemplate != null) {
            SelectQueryBuilder queryBuilder = new SelectQueryBuilder();
            queryBuilder.withTable(INFORMATION_SCHEMA, COLUMNS_TABLE)
                    .addColumns(TABLE_CATALOG, TABLE_SCHEMA, TABLE_NAME, COLUMN_NAME, ORDINAL_POSITION, COLUMN_DEFAULT,
                            IS_NULLABLE, DATA_TYPE, CHARACTER_MAXIMUM_LENGTH, CHARACTER_OCTET_LENGTH, NUMERIC_PRECISION,
                            NUMERIC_PRECISION_RADIX, NUMERIC_SCALE, DATETIME_PRECISION, INTERVAL_TYPE, INTERVAL_PRECISION,
                            CHARACTER_SET_CATALOG, CHARACTER_SET_SCHEMA, CHARACTER_SET_NAME, COLLATION_CATALOG, COLLATION_SCHEMA,
                            COLLATION_NAME, DOMAIN_CATALOG, DOMAIN_SCHEMA, DOMAIN_NAME, UDT_CATALOG, UDT_SCHEMA, UDT_NAME, SCOPE_CATALOG,
                            SCOPE_SCHEMA, SCOPE_NAME, MAXIMUM_CARDINALITY, DTD_IDENTIFIER, IS_SELF_REFERENCING, IS_IDENTITY, IDENTITY_GENERATION,
                            IDENTITY_START, IDENTITY_INCREMENT, IDENTITY_MAXIMUM, IDENTITY_MINIMUM, IDENTITY_CYCLE, IS_GENERATED,
                            GENERATION_EXPRESSION, IS_UPDATABLE)
                    .addJoinedColumns(PK_TABLE, CONSTRAINT_CATALOG, CONSTRAINT_SCHEMA, CONSTRAINT_NAME)
                    .addWhere(StringUtils.isEmpty(schema) ? null : String.format(SCHEMA_FILTER, schema))
                    .addWhere(StringUtils.isEmpty(tableName) ? null : String.format(TABLE_FILTER, tableName))
                    .leftJoin(INFORMATION_SCHEMA, PK_TABLE, COLUMN_NAME, TABLE_SCHEMA, TABLE_NAME )
                    .withPage(page).withSize(size);
            Long total = jdbcTemplate.queryForObject(queryBuilder.buildCountQuery(),
                    (rs, rowNum) -> rs.getLong(1));

            List<ColumnDto> columnDtos = jdbcTemplate.query(queryBuilder.build(), COLUMN_ROW_MAPPER);
            return new PageImpl<>(columnDtos, PageRequest.of(page, size), Objects.nonNull(total) ? total : columnDtos.size());
        }
        return Page.empty();
    }

}
