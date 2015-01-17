package org.guppy4j.jdbc;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
             CallableStatement call = conn.prepareCall(query.getSql())) {

            query.setParams(call);
            call.execute();

        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    @Override
    public int execute(Query query) {

        try (Connection conn = ds.getConnection();
             PreparedStatement ps = prepare(conn, query)) {

            query.setParams(ps);
            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    @Override
    public int[] executeBatch(Query query) {

        try (Connection conn = ds.getConnection();
             PreparedStatement ps = prepare(conn, query)) {

            query.setParams(ps);
            return ps.executeBatch();

        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    @Override
    public <T> T query(QueryWithResult<T> query) {

        try (Connection conn = ds.getConnection();
             PreparedStatement ps = prepare(conn, query)) {

            final ResultSet rs = execute(query, ps);
            return rs.next() ? query.getResult(rs) : null;

        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    @Override
    public <T> List<T> list(QueryWithResult<T> query) {

        try (Connection conn = ds.getConnection();
             PreparedStatement ps = prepare(conn, query)) {

            final ResultSet rs = execute(query, ps);
            final List<T> list = new ArrayList<>();
            while (rs.next()) {
                list.add(query.getResult(rs));
            }
            return list;

        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    @Override
    public void iterate(IteratingQuery query) {

        try (Connection conn = ds.getConnection();
             PreparedStatement ps = prepare(conn, query)) {

            final ResultSet rs = execute(query, ps);
            while (rs.next()) {
                query.next(rs);
            }
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    private PreparedStatement prepare(Connection connection, Query query)
            throws SQLException {
        return connection.prepareStatement(query.getSql());
    }

    private ResultSet execute(Query query, PreparedStatement ps)
            throws SQLException {
        query.setParams(ps);
        return ps.executeQuery();
    }
}