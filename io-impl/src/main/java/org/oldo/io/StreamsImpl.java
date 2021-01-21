package org.oldo.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Default stream helper
 */
public class StreamsImpl implements Streams {

    private static final int END_OF_STREAM = -1;

    private final int bufferSize;

    public StreamsImpl(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    @Override
    public byte[] allBytes(InputStream in) {
        final ByteArrayOutputStream out = new ByteArrayOutputStream(bufferSize);
        copy(in, out);
        return out.toByteArray();
    }

    @Override
    public int copy(InputStream inputStream, OutputStream outputStream) {
        final byte[] buf = new byte[bufferSize];
        int total = 0;
        // try-with-resources auto-closes the streams in a hidden finally block
        try (InputStream in = inputStream; OutputStream out = outputStream) {
            for (int read = in.read(buf);
                 read != END_OF_STREAM;
                 read = in.read(buf)) {

                out.write(buf, 0, read);
                total += read;
            }
            out.flush();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return total;
    }
}
