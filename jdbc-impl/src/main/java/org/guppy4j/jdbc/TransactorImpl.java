package org.guppy4j.jdbc;

import org.guppy4j.exceptions.ActionToTry;
import org.guppy4j.exceptions.ExceptionHandler;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * JDBC transaction support
 */
public class TransactorImpl implements Transactor {

    private final Connection connection;

    private final ExceptionHandler<SQLException> exHandler;

    public TransactorImpl(Connection connection,
                          ExceptionHandler<SQLException> exHandler) {
        this.connection = connection;
        this.exHandler = exHandler;
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

    private ActionToTry<SQLException> setAutoCommit(boolean autoCommit) {
        return () -> connection.setAutoCommit(autoCommit);
    }

    private void tryJdbc(ActionToTry<SQLException> jdbcAction) {
        exHandler.tryUnchecked(jdbcAction);
    }
}
