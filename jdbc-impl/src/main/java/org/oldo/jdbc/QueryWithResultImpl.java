package org.oldo.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * QueryWithResult implementation
 */
public class QueryWithResultImpl<T> extends QueryImpl implements QueryWithResult<T> {

    private final ResultMapper<T> mapper;

    public QueryWithResultImpl(String sql, ResultMapper<T> mapper,
                               ParamsSetter... paramsSetters) {
        super(sql, paramsSetters);
        this.mapper = mapper;
    }

    @Override
    public T getResult(ResultSet rs) throws SQLException {
        return mapper.getResult(rs);
    }
}
