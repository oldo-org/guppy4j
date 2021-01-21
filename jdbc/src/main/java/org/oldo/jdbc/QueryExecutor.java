package org.oldo.jdbc;

import java.util.List;

/**
 * Executes stored procedure calls and queries
 */
public interface QueryExecutor {

    /**
     * Calls a stored procedure
     *
     * @param query The query to execute (must be a callable statement)
     * @throws JdbcException From all underlying JDBC calls
     */
    void call(Query query);

    /**
     * Executes DML (Data Manipulation Language) queries
     * (i.e. typically INSERT, UPDATE, DELETE statements) or
     * other SQL queries (e.g. DDL) that have no result
     *
     * @param query The query to execute
     * @return Either the row count for SQL Data Manipulation Language (DML) statements
     * or 0 for SQL statements that return nothing
     * @throws JdbcException From all underlying JDBC calls
     */
    int execute(Query query);

    /**
     * Executes DML (Data Manipulation Language) queries
     * (i.e. typically INSERT, UPDATE, DELETE statements) or
     * SQL queries that have no result, using batch execution
     *
     * @param query The query to execute
     * @return Either the row counts for SQL Data Manipulation Language (DML)
     * statements or 0 for SQL statements that return nothing
     * @throws JdbcException From all underlying JDBC calls
     */
    int[] executeBatch(Query query);

    /**
     * Executes SELECT statements and maps resultSet to result object
     *
     * @param query The query to execute
     * @param <T>   The result type
     * @return The resulting object (created from the result set)
     * or null if result set was empty
     * @throws JdbcException From all underlying JDBC calls
     */
    <T> T query(QueryWithResult<T> query);

    /**
     * Executes SELECT statements and returns all results as a list
     *
     * @param query The query to execute
     * @param <T>   The result type
     * @return The list of all resulting objects (retrieved from the result
     * set). The list may be empty if the result set was empty.
     * @throws JdbcException From all underlying JDBC calls
     */
    <T> List<T> list(QueryWithResult<T> query);

    /**
     * Executes SELECT statements and iterates over all results
     *
     * @param query The iterating query
     * @throws JdbcException From all underlying JDBC calls
     */
    void iterate(IteratingQuery query);
}
