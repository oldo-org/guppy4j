package org.oldo.io;

/**
 * Media type enumeration
 */
public enum MediaTypeEnum implements MediaType {

    audio,
    image("images"),
    text;

    private final String groupingName;

    MediaTypeEnum() {
        groupingName = name();
    }

    MediaTypeEnum(String groupingName) {
        this.groupingName = groupingName;
    }

    @Override
    public boolean isAudio() {
        return this == audio;
    }

    @Override
    public boolean isImage() {
        return this == image;
    }

    @Override
    public boolean isText() {
        return this == text;
    }

    @Override
    public String getGroupingName() {
        return groupingName;
    }

    @Override
    public Iterable<FileType> getFileTypes() {
        return FileTypeEnum.values(this);
    }
}
