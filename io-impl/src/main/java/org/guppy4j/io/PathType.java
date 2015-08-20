package org.guppy4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Creation of file or directory, respectively
 */
public enum PathType implements PathCreator {

    FILE() {
        @Override
        public Path create(Path path) throws IOException {
            return Files.createFile(path);
        }
    },

    DIRECTORY() {
        @Override
        public Path create(Path path) throws IOException {
            return Files.createDirectories(path);
        }
    }
}