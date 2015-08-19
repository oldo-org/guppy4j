package org.guppy4j.jdbc;

/**
 * JDBC transaction support
 */
public interface Transactor {

    void begin();

    void commit();

    void rollback();
}
