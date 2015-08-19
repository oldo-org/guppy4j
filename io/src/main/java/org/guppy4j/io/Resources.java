package org.guppy4j.io;

import java.net.URL;
import java.util.Properties;

/**
 * Utility functions for loading text files or properties
 * from URLs or classpath locations
 */
public interface Resources {

    String content(URL url);

    String content(String resourceLocation);

    Properties properties(URL url);

    Properties properties(String resourceLocation);
}
