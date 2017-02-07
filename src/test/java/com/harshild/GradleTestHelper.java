package com.harshild;

import org.gradle.api.Project;
import org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency;
import org.gradle.testfixtures.ProjectBuilder;
import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by harshild on 2/6/2017.
 */
public class GradleTestHelper {
    public static BuildResult executeBuild(TemporaryFolder testProjectDir, String... tasks) {
        return GradleRunner.create()
                .withProjectDir(testProjectDir.getRoot())
                .withArguments(tasks)
                .forwardOutput()
                .withPluginClasspath()
                .build();
    }

    public static void writeFile(File destination, String content) throws IOException {
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

    public static void addCompileDependency(Project project, String groupName, String artifactName, String artifactVersion) {
        project.getConfigurations()
                .getByName("compile")
                .getDependencies()
                .add(new DefaultExternalModuleDependency(groupName, artifactName, artifactVersion));
    }

    public static Project buildProject(String projectName,TemporaryFolder projectDir) {
        return ProjectBuilder.builder()
                .withName(projectName)
                .withProjectDir(projectDir.getRoot())
                .build();
    }

}
