package org.guppy4j.io;

import java.io.IOException;
import java.nio.file.Path;

import static java.nio.file.Files.createDirectories;
import static java.nio.file.Files.createFile;

/**
 * Creation of file or directory, respectively
 */
public enum PathType implements PathCreator {

    FILE() {
        @Override
        public Path create(Path path) throws IOException {
            return createFile(path);
        }
    },

    DIRECTORY() {
        @Override
        public Path create(Path path) throws IOException {
            return createDirectories(path);
        }
    }
}