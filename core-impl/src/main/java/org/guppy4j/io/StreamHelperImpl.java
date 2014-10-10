package org.guppy4j.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Default stream helper
 */
public class StreamHelperImpl implements StreamHelper {

    private final int bufferSize;

    public StreamHelperImpl(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    @Override
    public byte[] copyToByteArray(InputStream in) throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream(bufferSize);
        copy(in, out);
        return out.toByteArray();
    }

    @Override
    public int copy(InputStream in, OutputStream out) throws IOException {
        try {
            int byteCount = 0;
            byte[] buffer = new byte[bufferSize];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
                byteCount += bytesRead;
            }
            out.flush();
            return byteCount;
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                // ignore (nothing we can do)
            }
            try {
                out.close();
            } catch (IOException ex) {
                // ignore (nothing we can do)
            }
        }
    }
}
