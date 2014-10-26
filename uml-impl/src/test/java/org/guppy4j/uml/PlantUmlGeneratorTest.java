package org.guppy4j.uml;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.DirectoryStream.Filter;
import static java.nio.file.Files.isDirectory;
import static java.nio.file.Files.newDirectoryStream;

/**
 * Tests PlantUmlGenerator
 */
public class PlantUmlGeneratorTest {

    private final PlantUmlGenerator generator = new PlantUmlGenerator();

    @Test
    public void test() throws IOException {

        final Iterable<Path> subDirs = newDirectoryStream(Paths.get(""),
                new Filter<Path>() {
                    @Override
                    public boolean accept(Path path) throws IOException {
                        return isDirectory(path) && isNotDotDir(path);
                    }
                });

        for (Path dir : subDirs) {
            final String dirPath = dir.toAbsolutePath().toString();
            generator.run(
                    "-b", dirPath,
                    "-o", dirPath + "/classes.plantuml",
                    "-dp", "^org.guppy4j.*");
        }
    }

    private static boolean isNotDotDir(Path path) {
        return !path.getFileName().toString().startsWith(".");
    }
}
