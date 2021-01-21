package org.oldo.uml;

import java.io.IOException;
import java.nio.file.Paths;

import org.junit.Test;

/**
 * Tests DependencyPlantUmlGenerator
 */
public class DependencyPlantUmlGeneratorTest {

    private final DependencyPlantUmlGenerator generator = new DependencyPlantUmlGenerator();

    @Test
    public void test() {
        generator.generate(getClass().getPackage(), Paths.get(""), "classes.plantuml");
    }
}
