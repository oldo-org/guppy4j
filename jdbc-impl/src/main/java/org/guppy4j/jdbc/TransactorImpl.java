package org.guppy4j.jdbc;

import org.guppy4j.Exceptions.DoSomething;

import java.sql.Connection;
import java.sql.SQLException;

import static org.guppy4j.Exceptions.tryCatchWrap;

/**
 * JDBC transaction support
 */
public class TransactorImpl implements Transactor {

    private final Connection connection;

    public TransactorImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void begin() {
        tryJdbc(setAutoCommit(false));
    }

    @Override
    public void commit() {
        tryJdbc(connection::commit);
        tryJdbc(setAutoCommit(true));
    }

    @Override
    public void rollback() {
        tryJdbc(connection::rollback);
        tryJdbc(setAutoCommit(true));
    }

    private DoSomething<SQLException> setAutoCommit(boolean autoCommit) {
        return () -> connection.setAutoCommit(autoCommit);
    }

    private static void tryJdbc(DoSomething<SQLException> something) {
        tryCatchWrap(something, SQLException.class, JdbcException::new);
    }
}
