package org.oldo.jdbc;

/**
 * Represents a JDBC query
 */
public interface Query extends ParamsSetter {

    /**
     * @return The SQL code for this query (using the usual JDBC placeholders
     * for prepared statement parameters, if needed)
     */
    String sql();

}
