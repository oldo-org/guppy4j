package org.guppy4j.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Creates an object from a JDBC ResultSet
 *
 * @param <T> The result type
 */
public interface ResultMapper<T> {

    /**
     * @param rs The result set from a query execution
     * @return The result object of type T
     * @throws java.sql.SQLException If thrown by the JDBC API
     */
    T getResult(ResultSet rs) throws SQLException;

}
