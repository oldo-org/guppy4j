package org.guppy4j.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Query implementation
 */
public class QueryImpl implements Query {

    private final String sql;
    private final ParamsSetter[] paramsSetters;

    public QueryImpl(String sql, ParamsSetter... paramsSetters) {
        this.sql = sql;
        this.paramsSetters = paramsSetters;
    }

    @Override
    public String sql() {
        return sql;
    }

    @Override
    public void setParams(PreparedStatement ps) throws SQLException {
        for (ParamsSetter paramsSetter : paramsSetters) {
            paramsSetter.setParams(ps);
        }
    }
}
