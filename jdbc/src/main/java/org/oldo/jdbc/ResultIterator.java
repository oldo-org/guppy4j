package org.oldo.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Iterates through a JDBC ResultSet
 */
public interface ResultIterator {

    void next(ResultSet rs) throws SQLException;

}
