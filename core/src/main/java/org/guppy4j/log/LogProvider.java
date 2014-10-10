package org.guppy4j.log;

/**
 * Provides log instances
 */
public interface LogProvider {

    Log getLog(Class<?> c);

}
