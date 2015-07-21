package org.guppy4j;

import java.net.URL;
import java.nio.file.Path;

/**
 * Type-safe access to standard
 * <a href="http://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html">
 * System properties</a>
 *
 * @see System#getProperties()
 */
public interface SystemInfo {

    /**
     * @return File path component separator, e.g. \ on Windows, / on Unix
     * @see "The file.separator system property"
     */
    char fileSeparator();

    /**
     * @return Separator for jar and directory paths in classpath and system path,
     * e.g. ; on Windows, : on Unix
     * @see "The path.separator system property"
     */
    char pathSeparator();

    /**
     * @return Character sequence that separates lines in text files
     * @see "The line.separator system property"
     */
    String lineSeparator();

    /**
     * @return Ordered list of directories and JAR files, see
     * <a href="http://docs.oracle.com/javase/8/docs/technotes/tools/windows/classpath.html">
     * Classpath documentation</a> for details
     * @see "The java.class.path system property"
     */
    Iterable<Path> javaClassPath();

    /**
     * @return Paths of extension directory or directories, see the
     * <a href="http://docs.oracle.com/javase/7/docs/technotes/guides/extensions/spec.html">
     * Extension mechanism</a> spec for details
     * @see "The java.ext.dirs system property"
     */
    Iterable<Path> javaExtDirs();

    /**
     * @return Installation directory for Java Runtime Environment (JRE),
     * @see "The java.home system property"
     */
    Path javaHome();

    /**
     * @return Default system directory for temporary files
     * @see "The java.io.tmpdir system property"
     */
    Path javaIoTmpDir();

    /**
     * @return Current working directory of this Java process
     * @see "The user.dir system property"
     */
    Path userDir();

    /**
     * @return User home directory
     * @see "The user.home system property"
     * @see "http://bugs.java.com/view_bug.do?bug_id=4787931"
     */
    Path userHome();

    /**
     * @return Name of JIT compiler to use
     * @see Compiler
     * @see "The java.compiler system property"
     */
    String javaCompiler();

    /**
     * @return JRE vendor name
     * @see "The java.vendor system property"
     */
    String javaVendor();

    /**
     * @return JRE vendor URL
     * @see "The java.vendor.url system property"
     */
    URL javaVendorUrl();

    /**
     * @return JRE version number
     * @see "The java.version system property"
     */
    String javaVersion();

    /**
     * @return Operating system architecture
     * @see "The os.arch system property"
     */
    String osArch();

    /**
     * @return Operating system name
     * @see "The os.name system property"
     */
    String osName();

    /**
     * @return Operating system version
     * @see "The os.version system property"
     */
    String osVersion();

    /**
     * @return User account name
     * @see "The user.name system property"
     */
    String userName();
}
