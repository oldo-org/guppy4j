package org.oldo.jdbc;

/**
 * Executes SQL statements
 */
public class SqlExecutorImpl implements SqlExecutor {

    private final QueryExecutor qe;
    private final QueryFactory qf;

    public SqlExecutorImpl(QueryExecutor qe, QueryFactory qf) {
        this.qe = qe;
        this.qf = qf;
    }

    @Override
    public int execute(String sql, ParamsSetter... paramsSetters) {
        return qe.execute(qf.query(sql, paramsSetters));
    }

    @Override
    public void iterate(String sql, ResultIterator iterator, ParamsSetter... paramsSetters) {
        qe.iterate(qf.iteratingQuery(sql, iterator, paramsSetters));
    }

    @Override
    public <T> T query(String sql, ResultMapper<T> mapper, ParamsSetter... paramsSetters) {
        return qe.query(qf.queryWithResult(sql, mapper, paramsSetters));
    }
}
