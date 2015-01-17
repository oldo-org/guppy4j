package org.guppy4j.jdbc;

import org.h2.jdbcx.JdbcDataSource;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

/**
 * Tests SqlRunnerImpl
 */
public class SqlExecutorImplTest {

    private final SqlExecutor se;

    public SqlExecutorImplTest() {

        final JdbcDataSource ds = new JdbcDataSource();
        ds.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        ds.setUser("sa");

        se = new SqlExecutorImpl(new QueryExecutorImpl(ds), new QueryFactoryImpl());
    }

    @Test
    public void test() {

        se.execute("CREATE TABLE test (id INT)");
        se.execute("INSERT INTO test (id) VALUES (1) ");

        final int id = se.query("SELECT id FROM test",
                new ResultMapper<Integer>() {
                    @Override
                    public Integer getResult(ResultSet rs) throws SQLException {
                        return rs.getInt(1);
                    }
                });

        assertEquals(1, id);
    }
}
