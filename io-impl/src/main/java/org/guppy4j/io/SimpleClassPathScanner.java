package org.guppy4j.io;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.DirectoryStream.Filter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Lists sub-directories for classpath (file and jar) URLs
 */
public final class SimpleClassPathScanner implements DirectoryLister {

    @Override
    public Iterable<String> getSubDirectories(URL url) {
        final String protocol = url.getProtocol();
        final Set<String> result = new HashSet<>();
        if ("file".equals(protocol)) {
            getPaths(Paths.get(getUri(url)), Files::isDirectory).forEach(
                    p -> result.add(p.getFileName().toString())
            );
            return result;
        }
        if ("jar".equals(protocol)) {
            final String urlPath = url.getPath();
            final int separatorIndex = urlPath.indexOf('!');
            final String jarPath = urlPath.substring("file:".length(), separatorIndex);
            final String path = urlPath.substring(separatorIndex + 1);
            final String baseEntryPath = (path.startsWith("/") ? path.substring(1) : path) + '/';
            for (Enumeration<JarEntry> entries = getJarFile(jarPath).entries(); entries.hasMoreElements(); ) {
                final String entryPath = entries.nextElement().getName();
                if (entryPath.startsWith(baseEntryPath) && entryPath.endsWith("/")) {
                    final String entry = entryPath.substring(baseEntryPath.length());
                    final int slashIndex = entry.indexOf('/');
                    if (slashIndex != -1) {
                        final String dir = entry.substring(0, slashIndex);
                        if (!dir.isEmpty()) {
                            result.add(dir);
                        }
                    }
                }
            }
            return result;
        }
        throw new IllegalArgumentException("Cannot list contents of :" + url);
    }

    private static URI getUri(URL url) {
        try {
            return url.toURI();
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static Iterable<Path> getPaths(Path dir, Filter<Path> filter) {
        try {
            return Files.newDirectoryStream(dir, filter);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private static JarFile getJarFile(String jarPath) {
        try {
            return new JarFile(URLDecoder.decode(jarPath, "UTF-8"));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
