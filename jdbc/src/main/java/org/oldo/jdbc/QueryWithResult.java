package org.oldo.jdbc;

/**
 * Represents a JDBC query with result(s), typically a SELECT
 *
 * @param <T> The result type
 */
public interface QueryWithResult<T> extends Query, ResultMapper<T> {

    // no additional methods

}
