package org.guppy4j.io;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

/**
 * Helps with management of application files (paths)_
 */
public interface Paths {

    Path findOrCreate(String name, PathCreator type);

    Path writeFile(InputStream stream, Path targetDir, String name, String extension) throws IOException;
}
