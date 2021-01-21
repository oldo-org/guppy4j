package org.oldo.log;

import org.slf4j.LoggerFactory;

/**
 * Provides log instances based on slf4j
 */
public class Slf4jLogProvider implements LogProvider {

    @Override
    public Log getLog(Class<?> clazz) {
        return new Slf4jLog(LoggerFactory.getLogger(clazz));
    }
}
