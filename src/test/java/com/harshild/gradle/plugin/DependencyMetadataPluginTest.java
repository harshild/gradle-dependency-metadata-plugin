package com.harshild.gradle.plugin;

import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.gradle.testkit.runner.TaskOutcome.SUCCESS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Created by W18NM36 on 2/6/2017.
 */
public class DependencyMetadataPluginTest {
    @Rule
    public final TemporaryFolder testProjectDir = new TemporaryFolder();
    private File buildFile;

    @Before
    public void setup() throws IOException {
        buildFile = testProjectDir.newFile("build.gradle");
        String buildFileContent = "plugins {" +
                "    id 'com.harshild.dep-metadata'" +
                "}";
        writeFile(buildFile, buildFileContent);
    }

    @Test
    public void DMPlugin_has_reportGeneratorTask() throws IOException {
        BuildResult result = executeBuild("tasks","--all");
        assertEquals(result.task(":tasks").getOutcome(), SUCCESS);
        assertTrue(result.getOutput().contains("generateDependencyMetadataReport"));
    }

    @Test
    public void DMPlugin_canExecute_reportGeneratorTask() throws IOException {
        BuildResult result = executeBuild("generateDependencyMetadataReport");
        assertEquals(result.task(":generateDependencyMetadataReport").getOutcome(), SUCCESS);
    }

    @Test
    public void DMPlugin_shouldRun_Build_before_reportGeneratorTask() throws IOException {
        BuildResult result = executeBuild("generateDependencyMetadataReport");
        assertEquals(result.task(":build").getOutcome(), SUCCESS);
    }

    private BuildResult executeBuild(String... tasks) {
        return GradleRunner.create()
                .withProjectDir(testProjectDir.getRoot())
                .withArguments(tasks)
                .forwardOutput()
                .withPluginClasspath()
                .build();
    }

    private void writeFile(File destination, String content) throws IOException {
        BufferedWriter output = null;
        try {
            output = new BufferedWriter(new FileWriter(destination));
            output.write(content);
        } finally {
            if (output != null) {
                output.close();
            }
        }
    }
}