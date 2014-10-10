package org.guppy4j.io;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Default resource helper
 */
public class ResourceHelperImpl implements ResourceHelper {

    private final StreamHelper streamHelper;

    public ResourceHelperImpl(StreamHelper streamHelper) {
        this.streamHelper = streamHelper;
    }

    @Override
    public String getResourceAsString(String resourceLocation) throws IOException {
        final InputStream stream = getClass().getResourceAsStream(resourceLocation);
        final byte[] bytes = streamHelper.copyToByteArray(stream);
        return new String(bytes, Charset.forName("UTF-8"));
    }
}
