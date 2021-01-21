package org.oldo.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Returns a result based on a given PreparedStatement
 */
public interface JdbcAction<R> {

    /**
     * @param ps A PreparedStatement
     * @return A result of type R
     */
    R execute(PreparedStatement ps) throws SQLException;
}
