package org.oldo.jdbc;

/**
 * Constructs queries, typically used in
 * combination with QueryExecutor
 */
public interface QueryFactory {

    /**
     * @param sql           The SQL query
     * @param paramsSetters The (optional) parameter setter(s)
     * @return A corresponding Query instance
     */
    Query query(String sql, ParamsSetter... paramsSetters);

    /**
     * @param sql           The SQL query
     * @param iterator      The result iterator
     * @param paramsSetters The (optional) parameter setter(s)
     * @return A corresponding IteratingQuery instance
     */
    IteratingQuery iteratingQuery(String sql, ResultIterator iterator,
                                  ParamsSetter... paramsSetters);

    /**
     * @param sql           The SQL query
     * @param mapper        The result mapper
     * @param paramsSetters The (optional) parameter setter(s)
     * @param <T>           Query result type
     * @return A corresponding QueryWithResult instance
     */
    <T> QueryWithResult<T> queryWithResult(String sql, ResultMapper<T> mapper,
                                           ParamsSetter... paramsSetters);
}
