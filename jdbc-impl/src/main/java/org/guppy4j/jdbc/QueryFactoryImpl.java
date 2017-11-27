package org.guppy4j.jdbc;

/**
 * Constructs queries
 */
public class QueryFactoryImpl implements QueryFactory {

    @Override
    public Query query(final String sql,
                       final ParamsSetter... paramsSetters) {
        return new QueryImpl(sql, paramsSetters);
    }

    @Override
    public IteratingQuery iteratingQuery(String sql,
                                         ResultIterator iterator,
                                         ParamsSetter... paramsSetters) {
        return new IteratingQueryImpl(sql, iterator, paramsSetters);
    }

    @Override
    public <T> QueryWithResult<T> queryWithResult(String sql,
                                                  ResultMapper<T> mapper,
                                                  ParamsSetter... paramsSetters) {
        return new QueryWithResultImpl<>(sql, mapper, paramsSetters);
    }
}
