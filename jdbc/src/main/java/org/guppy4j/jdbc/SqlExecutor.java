package org.guppy4j.jdbc;

/**
 * Executes SQL statements
 */
public interface SqlExecutor {

    int execute(String sql, ParamsSetter... paramsSetters);

    void iterate(String sql, ResultIterator iterator, ParamsSetter... paramsSetters);

    <T> T query(String sql, ResultMapper<T> mapper, ParamsSetter... paramsSetters);
}
