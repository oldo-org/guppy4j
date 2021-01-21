package org.oldo.io;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Utility functions for working with I/O streams
 */
public interface Streams {

    /**
     * Copy the contents of the given InputStream into a new byte array.
     * Leaves the stream open when done.
     *
     * @param in the stream to copy from
     * @return the new byte array that has been copied to
     * @throws IllegalStateException in case of I/O errors
     */
    byte[] allBytes(InputStream in) throws IllegalStateException;

    /**
     * Copy the contents of the given InputStream to the given OutputStream.
     * Closes both streams when done.
     *
     * @param in  the InputStream to copy from
     * @param out the OutputStream to copy to
     * @return the number of bytes copied
     * @throws IllegalStateException in case of I/O errors
     */
    int copy(InputStream in, OutputStream out) throws IllegalStateException;

}
