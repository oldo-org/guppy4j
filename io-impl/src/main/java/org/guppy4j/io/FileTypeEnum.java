package org.guppy4j.io;

import java.util.Collection;
import java.util.LinkedHashSet;

import static org.guppy4j.io.MediaTypeEnum.audio;
import static org.guppy4j.io.MediaTypeEnum.image;
import static org.guppy4j.io.MediaTypeEnum.text;

/**
 * File type enumeration
 */
public enum FileTypeEnum implements FileType {

    ogg(audio),
    mp3(audio),
    wav(audio),
    au(audio),

    png(image),
    jpg(image),
    gif(image),

    csv(text);

    private final MediaTypeEnum mediaType;

    FileTypeEnum(MediaTypeEnum mediaType) {
        this.mediaType = mediaType;
    }

    @Override
    public MediaType mediaType() {
        return mediaType;
    }

    @Override
    public String fileName(String baseName) {
        return baseName + '.' + name();
    }

    @Override
    public String mimeType() {
        return mediaType.name() + "/" + name();
    }

    public static Iterable<FileType> values(MediaTypeEnum mediaTypeEnum) {
        final Collection<FileType> result = new LinkedHashSet<>();
        for (FileTypeEnum fte : values()) {
            if (fte.mediaType == mediaTypeEnum) {
                result.add(fte);
            }
        }
        return result;
    }


}
