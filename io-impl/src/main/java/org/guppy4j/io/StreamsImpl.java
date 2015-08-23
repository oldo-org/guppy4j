package org.guppy4j.io;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Default stream helper
 */
public class StreamsImpl implements Streams {

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
    public int copy(InputStream in, OutputStream out) {
        final byte[] buf = new byte[bufferSize];
        int total = 0;
        try {
            for (int read = in.read(buf);
                 read != -1;
                 read = in.read(buf)) {

                out.write(buf, 0, read);
                total += read;
            }
            out.flush();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        } finally {
            close(in);
            close(out);
        }
        return total;
    }

    private void close(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException ex) {
            // ignore (nothing we can do)
            // TODO : log a warning
        }
    }
}
