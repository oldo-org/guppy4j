package org.guppy4j.io;

import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.util.Properties;

import org.guppy4j.exceptions.FunctionToTry;

import static org.guppy4j.io.Charsets.UTF_8;

/**
 * Default resource helper
 */
public final class ResourcesImpl implements Resources {

    private static final Class<?> myClass = MethodHandles.lookup().lookupClass();

    private final Streams streams;

    public ResourcesImpl(Streams streams) {
        this.streams = streams;
    }

    @Override
    public String content(String resourceLocation) {
        return content(url(resourceLocation));
    }

    @Override
    public String content(URL url) {
        return open(url, this::content);
    }

    private String content(InputStream stream) throws IOException {
        return new String(streams.allBytes(stream), UTF_8);
    }

    @Override
    public Properties properties(String resourceLocation) {
        return properties(url(resourceLocation));
    }

    @Override
    public Properties properties(URL url) {
        return open(url, this::properties);
    }

    private Properties properties(InputStream stream) throws IOException {
        final Properties p = new Properties();
        p.load(stream);
        return p;
    }

    private static URL url(String resourceLocation) {
        return myClass.getResource(resourceLocation);
    }

    private static <T> T open(URL url, FunctionToTry<InputStream, T, IOException> streamLoader) {
        try (InputStream stream = url.openStream()) {
            return streamLoader.apply(stream);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
