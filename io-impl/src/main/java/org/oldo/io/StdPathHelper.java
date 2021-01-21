package org.oldo.io;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;

import org.oldo.log.Log;
import org.oldo.log.LogProvider;

import static java.nio.file.Files.copy;
import static org.oldo.io.PathType.DIRECTORY;

public final class StdPathHelper implements Paths {

    private final Path appDir;
    private final Log log; // TODO: Use the log !

    public StdPathHelper(String appName, Path homeDir,
                         LogProvider logProvider) {
        log = logProvider.getLog(getClass());
        appDir = homeDir.resolve('.' + appName.toLowerCase());
        createIfNecessary(appDir, DIRECTORY);
    }

    @Override
    public Path findOrCreate(String name, PathCreator type) {
        return createIfNecessary(appDir.resolve(name), type);
    }

    @Override
    public Path writeFile(InputStream stream, Path targetDir,
                          String name, String extension) throws IOException {
        final Path path = resolveIn(targetDir, name, extension);
        copy(stream, path);
        return path;
    }

    private static Path resolveIn(Path dir, String name, String extension) {
        return dir.resolve(sanitize(name) + '.' + extension);
    }

    private static String sanitize(String name) {
        return name.replace(" ", "_");
    }

    private static Path createIfNecessary(Path path, PathCreator creator) {
        try {
            return creator.create(path);
        } catch (FileAlreadyExistsException e) {
            // ok, that's fine
            return path;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
