package com.task.databaseinspector.util;

import org.springframework.util.StringUtils;

public class SelectQueryBuilder {
    private String tableName;
    private String schema;
    private StringBuilder where = new StringBuilder();
    private StringBuilder columns = new StringBuilder();
    private StringBuilder join = new StringBuilder();
    private int page;
    private int size;

    public SelectQueryBuilder withTable(String schema, String name) {
        tableName = name;
        this.schema = schema;
        return this;
    }

    public SelectQueryBuilder addWhere(String condition) {
        if (!StringUtils.isEmpty(condition)) {
            if (where.length() == 0) {
                where.append(" WHERE ").append(condition);
            } else {
                where.append("AND ").append(condition);
            }
            where.append(" ");
        }
        return this;
    }

    public SelectQueryBuilder addColumns(String... columns) {
        for (String column : columns) {
            if (this.columns.length() != 0) {
                this.columns.append(", ");
            }
            this.columns.append(tableName).append(".").append(column);
        }
        return this;
    }

    public SelectQueryBuilder addJoinedColumns(String tableName, String... columns) {
        for (String column : columns) {
            if (this.columns.length() != 0) {
                this.columns.append(", ");
            }
            this.columns.append(tableName).append(".").append(column);
        }
        return this;
    }

    public SelectQueryBuilder withPage(int page) {
        this.page = page;
        return this;
    }

    public SelectQueryBuilder withSize(int size) {
        this.size = size;
        return this;
    }

    public SelectQueryBuilder leftJoin(String schema, String tableName, String... onColumns) {
        join.append(" left join ").append(schema).append(".").append(tableName).append(" ON ");
        for (String onColumn : onColumns) {
            join.append(this.tableName).append(".").append(onColumn).append(" = ").append(schema).append(".").append(tableName).append(".").append(onColumn);
            join.append(" AND ");
        }
        join.delete(join.length() - 4, join.length());
        return this;
    }

    public String build() {
        return "SELECT " +
                (columns.length() == 0 ? "*" : columns.toString()) +
                " FROM " + constructFrom() +
                where.toString() +
                (size > 0 ? " LIMIT " + size : "") + " OFFSET " + page * size;
    }

    private String constructFrom() {
        return schema + "." + tableName + join.toString();
    }

    public String buildCountQuery() {
        return "SELECT COUNT(1) AS ROW_COUNT FROM " + constructFrom() + where.toString();
    }

}
