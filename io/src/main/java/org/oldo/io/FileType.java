package org.oldo.io;

/**
 * A file type
 */
public interface FileType {

    MediaType mediaType();

    String mimeType();

    String fileName(String baseName);

}
