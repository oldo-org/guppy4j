package org.oldo.io;

import java.io.InputStream;
import java.nio.file.Path;

/**
 * Reads and converts character data from various input sources
 * to a certain result type
 */
public interface CharDataConverter<T> {

    T from(CharSequence data);

    T from(InputStream stream);

    T from(Path path);
}
