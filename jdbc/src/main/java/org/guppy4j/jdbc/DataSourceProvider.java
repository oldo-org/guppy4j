package org.guppy4j.jdbc;

import javax.sql.DataSource;

/**
 * Provides DataSource object(s)
 */
public interface DataSourceProvider {

    /**
     * @return A DataSource instance
     */
    DataSource getDataSource();

    /**
     * Closes underlying resources, like connection pools
     */
    void close();

}
