package com.task.databaseinspector.dao.Impl;

import com.task.databaseinspector.busobj.dto.TableDto;
import com.task.databaseinspector.busobj.rowmapper.TableRowMapper;
import com.task.databaseinspector.cache.JdbcTemplateCache;
import com.task.databaseinspector.dao.TableDao;
import com.task.databaseinspector.util.SelectQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TableDaoImpl implements TableDao {

    private static final String TABLES_TABLE = "tables";

    private static final String SCHEMA_FILTER = TABLE_SCHEMA + "='%s'";
    private static final TableRowMapper TABLE_ROW_MAPPER = new TableRowMapper();


    @Autowired
    private JdbcTemplateCache jdbcTemplateCache;


    @Override
    public Page<TableDto> getAll(Long connectionId, String schema, int page, int size) {
        JdbcTemplate jdbcTemplate = jdbcTemplateCache.getJdbcTemplate(connectionId);
        if (jdbcTemplate != null) {
            SelectQueryBuilder queryBuilder = new SelectQueryBuilder();
            queryBuilder.withTable(INFORMATION_SCHEMA, TABLES_TABLE)
                    .addColumns(TABLE_CATALOG, TABLE_SCHEMA, TABLE_NAME, TABLE_TYPE,
                            SELF_REFERENCING_COLUMN_NAME, REFERENCE_GENERATION, USER_DEFINED_TYPE_CATALOG,
                            USER_DEFINED_TYPE_SCHEMA, USER_DEFINED_TYPE_NAME, IS_INSERTABLE_INTO, IS_TYPED, COMMIT_ACTION)
                    .addWhere(StringUtils.isEmpty(schema) ? null : String.format(SCHEMA_FILTER, schema))
                    .withPage(page).withSize(size);
            Long total = jdbcTemplate.queryForObject(queryBuilder.buildCountQuery(),
                    (rs, rowNum) -> rs.getLong(1));

            List<TableDto> tableDtos = jdbcTemplate.query(queryBuilder.build(), TABLE_ROW_MAPPER);
            return new PageImpl<>(tableDtos, PageRequest.of(page, size), Objects.nonNull(total) ? total : tableDtos.size());
        }
        return Page.empty();
    }
}
