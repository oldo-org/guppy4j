package org.guppy4j.config;

import java.net.URL;
import java.nio.file.Path;

/**
 * Type-safe access to standard
 * <a href="http://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html">
 * System properties</a>
 */
@SuppressWarnings("UnusedDeclaration")
public interface SystemInfo {

    /**
     * @return File path component separator, e.g. '\' on Windows, '/' on Unix
     * @see "file.separator" system property
     */
    char fileSeparator();

    /**
     * @return Separator for jar and directory paths in classpath and system path,
     * e.g. ';' on Windows, ':' on Unix
     * @see "path.separator" system property
     */
    char pathSeparator();

    /**
     * @return Character sequence that separates lines in text files
     * @see "line.separator" system property
     */
    String lineSeparator();

    /**
     * @return Ordered list of directories and JAR files, see
     * <a href="http://docs.oracle.com/javase/8/docs/technotes/tools/windows/classpath.html">
     * Classpath documentation</a> for details
     * @see "java.class.path" system property
     */
    Iterable<Path> javaClassPath();

    /**
     * @return Installation directory for Java Runtime Environment (JRE),
     * @see "java.home" system property
     */
    Path javaHome();

    /**
     * @return Current working directory of this Java process
     * @see "user.dir" system property
     */
    Path userDir();

    /**
     * @return User home directory
     * @see "user.home" system property
     * @see <a href="http://bugs.java.com/view_bug.do?bug_id=4787931">JDK-4787931</a> bug
     */
    Path userHome();

    /**
     * @return JRE vendor name
     * @see "java.vendor" system property
     */
    String javaVendor();

    /**
     * @return JRE vendor URL
     * @see "java.vendor.url" system property
     */
    URL javaVendorUrl();

    /**
     * @return JRE version number
     * @see "java.version" system property
     */
    String javaVersion();

    /**
     * @return Operating system architecture
     * @see "os.arch" system property
     */
    String osArch();

    /**
     * @return Operating system name
     * @see "os.name" system property
     */
    String osName();

    /**
     * @return Operating system version
     * @see "os.version" system property
     */
    String osVersion();

    /**
     * @return User account name
     * @see "user.name" system property
     */
    String userName();
}
