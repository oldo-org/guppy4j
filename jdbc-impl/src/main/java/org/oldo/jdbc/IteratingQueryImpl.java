package org.oldo.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * IteratingQuery implementation
 */
public class IteratingQueryImpl extends QueryImpl implements IteratingQuery {

    private final ResultIterator iterator;

    public IteratingQueryImpl(String sql, ResultIterator iterator,
                              ParamsSetter... paramsSetters) {
        super(sql, paramsSetters);
        this.iterator = iterator;
    }

    @Override
    public void next(ResultSet rs) throws SQLException {
        iterator.next(rs);
    }
}
