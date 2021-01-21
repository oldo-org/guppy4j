package org.oldo.io;

/**
 * Media type, mainly for audio and image files
 */
public interface MediaType {

    boolean isAudio();

    boolean isImage();

    boolean isText();

    String getGroupingName();

    Iterable<FileType> getFileTypes();
}
