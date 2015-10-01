package org.guppy4j.uml;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Tests DependencyPlantUmlGenerator
 */
public class DependencyPlantUmlGeneratorTest {

    private final DependencyPlantUmlGenerator generator = new DependencyPlantUmlGenerator();

    @Test
    public void test() throws IOException {
        generator.generate(getClass().getPackage(), Paths.get(""), "classes.plantuml");
    }
}
