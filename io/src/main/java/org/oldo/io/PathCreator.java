package org.oldo.io;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Physically creates a given path (file or directory)
 */
public interface PathCreator {

    Path create(Path path) throws IOException;

}
