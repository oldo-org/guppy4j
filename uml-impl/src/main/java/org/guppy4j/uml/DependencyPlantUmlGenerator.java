package org.guppy4j.uml;

import static java.lang.String.format;
import static java.nio.file.Files.isDirectory;
import static java.nio.file.Files.newDirectoryStream;

import net.sourceforge.plantumldependency.cli.main.program.PlantUMLDependencyProgram;
import net.sourceforge.plantumldependency.commoncli.command.impl.CommandLineImpl;
import net.sourceforge.plantumldependency.commoncli.exception.CommandLineException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.text.ParseException;

public class DependencyPlantUmlGenerator {

    private final PlantUMLDependencyProgram program;

    public DependencyPlantUmlGenerator() {
        try {
            program = new PlantUMLDependencyProgram();
        } catch (MalformedURLException | CommandLineException | ParseException e) {
            throw new IllegalStateException(e);
        }
    }

    public void generate(Package basePackage, Path baseDir, String targetFileName) {
        generate(format("^%s.*", basePackage.getName()), baseDir, targetFileName);
    }

    public void generate(String packageNamePattern, Path baseDir, String targetFileName) {
        for (Path dir : subDirs(baseDir)) {
            final String dirPath = dir.toAbsolutePath().toString();
            execute("-b", dirPath,
                    "-o", format("%s/%s", dirPath, targetFileName),
                    "-dp", packageNamePattern);
        }
    }

    private Iterable<Path> subDirs(Path baseDir) {
        try {
            return newDirectoryStream(baseDir, path -> isDirectory(path) && isNotDotDir(path));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private static boolean isNotDotDir(Path path) {
        return !path.getFileName().toString().startsWith(".");
    }

    /**
     * @param args PlantUML Dependency arguments as they would be written in the command line
     * @throws IllegalArgumentException Containing any thrown exception as the cause
     * @see <a href="http://plantuml-depend.sourceforge.net/command_line/command_line.html">
     * Command line examples</a>
     */
    private void execute(String... args) {
        final CommandLineImpl commandLine = new CommandLineImpl(args);
        try {
            program.parseCommandLine(commandLine).execute();
        } catch (CommandLineException e) {
            throw new IllegalArgumentException(e);
        }
    }
}