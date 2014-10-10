package org.guppy4j.io;

import java.io.IOException;

/**
 * Utility functions for working with classpath resources
 */
public interface ResourceHelper {

    String getResourceAsString(String resourceLocation) throws IOException;
}
