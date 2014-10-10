package org.guppy4j.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Sets JDBC query parameters
 */
public interface ParamsSetter {

    /**
     * Sets all parameters for this query (i.e. performs placeholder substitution
     * on the prepared statement)
     *
     * @param ps The prepared statement representing this query
     * @throws java.sql.SQLException If thrown by any JDBC calls
     */
    void setParams(PreparedStatement ps) throws SQLException;

}
