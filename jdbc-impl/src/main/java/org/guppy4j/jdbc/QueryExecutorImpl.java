package org.guppy4j.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

/**
 * Safely executes JDBC queries or updates
 */
public class QueryExecutorImpl implements QueryExecutor {

    private final DataSource ds;

    /**
     * @param dataSourceProvider The data source provider
     */
    public QueryExecutorImpl(DataSourceProvider dataSourceProvider) {
        this(dataSourceProvider.getDataSource());
    }

    public QueryExecutorImpl(DataSource dataSource) {
        ds = dataSource;
    }

    @Override
    public void call(Query query) {
        try (Connection conn = ds.getConnection();
             CallableStatement call = conn.prepareCall(query.sql())) {

            query.setParams(call);
            call.execute();

        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    @Override
    public int execute(Query query) {
        return tryStatement(query, (ps) -> {
            query.setParams(ps);
            return ps.executeUpdate();
        });
    }

    @Override
    public int[] executeBatch(Query query) {
        return tryStatement(query, (ps) -> {
            query.setParams(ps);
            return ps.executeBatch();
        });
    }

    @Override
    public <T> T query(QueryWithResult<T> query) {
        return tryStatement(query, (ps) -> {
            final ResultSet rs = execute(query, ps);
            return rs.next() ? query.getResult(rs) : null;
        });
    }

    @Override
    public <T> List<T> list(QueryWithResult<T> query) {
        final List<T> list = new ArrayList<>();
        iterate(new IteratingQueryImpl(query.sql(), rs -> list.add(query.getResult(rs)), query));
        return list;
    }

    @Override
    public void iterate(IteratingQuery query) {
        tryStatement(query, (ps) -> {
            final ResultSet rs = execute(query, ps);
            while (rs.next()) {
                query.next(rs);
            }
            return null;
        });
    }

    private <R> R tryStatement(Query query, JdbcAction<R> action) {
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(query.sql())) {
            return action.execute(ps);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    private static ResultSet execute(Query query, PreparedStatement ps) throws SQLException {
        query.setParams(ps);
        return ps.executeQuery();
    }
}