package org.guppy4j.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.function.Function;

import static org.guppy4j.Exceptions.tryTo;
import static org.guppy4j.io.Charsets.UTF_8;

/**
 * Default resource helper
 */
public final class ResourcesImpl implements Resources {

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
        return open(url, stream -> new String(streams.allBytes(stream), UTF_8));
    }

    @Override
    public Properties properties(String resourceLocation) {
        return properties(url(resourceLocation));
    }

    @Override
    public Properties properties(URL url) {
        return open(url, stream -> {
            final Properties p = new Properties();
            tryTo(() -> p.load(stream));
            return p;
        });
    }

    private URL url(String resourceLocation) {
        return getClass().getResource(resourceLocation);
    }

    private static <T> T open(URL url, Function<InputStream, T> streamLoader) {
        try (InputStream stream = url.openStream()) {
            return streamLoader.apply(stream);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
