package org.guppy4j.io;

import java.net.URL;

/**
 * Lists sub-directories
 */
@FunctionalInterface
public interface DirectoryLister {

    Iterable<String> getSubDirectories(URL url);

}
