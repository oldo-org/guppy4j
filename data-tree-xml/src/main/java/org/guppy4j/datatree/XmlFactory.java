package org.guppy4j.datatree;

import org.guppy4j.io.CharDataConverter;

import java.io.InputStream;
import java.nio.file.Path;

/**
 * Creates Xml objects from various input sources
 */
public class XmlFactory implements CharDataConverter<Xml> {

    @Override
    public Xml from(CharSequence payload) {
        return new Xml(payload);
    }

    @Override
    public Xml from(InputStream stream) {
        return new Xml(stream);
    }

    @Override
    public Xml from(Path path) {
        return new Xml(path);
    }
}
