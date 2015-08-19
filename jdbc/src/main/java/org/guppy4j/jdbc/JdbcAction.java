package org.guppy4j.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Returns a result based on a given PreparedStatement
 */
public interface JdbcAction<R> {

    /**
     * @param ps A PreparedStatement
     * @return A result of type R
     * @throws SQLException
     */
    R execute(PreparedStatement ps) throws SQLException;
}
