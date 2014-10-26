package org.guppy4j.uml;

import net.sourceforge.plantumldependency.cli.main.program.PlantUMLDependencyProgram;
import net.sourceforge.plantumldependency.commoncli.command.impl.CommandLineImpl;
import net.sourceforge.plantumldependency.commoncli.exception.CommandLineException;

import java.net.MalformedURLException;
import java.text.ParseException;

public class PlantUmlGenerator {

    /**
     * @param args PlantUML Dependency arguments as they would be written in the command line
     * @throws IllegalArgumentException Containing any thrown exception as the cause
     * @see <a href="http://plantuml-depend.sourceforge.net/command_line/command_line.html">
     * Command line examples</a>
     */
    public void run(String... args) {
        try {
            new PlantUMLDependencyProgram()
                    .parseCommandLine(new CommandLineImpl(args))
                    .execute();
        } catch (CommandLineException | MalformedURLException | ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }
}