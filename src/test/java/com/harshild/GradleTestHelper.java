package com.harshild;

import org.gradle.api.Project;
import org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.testfixtures.ProjectBuilder;
import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.Scanner;

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

    public static boolean makeDir(String path){
        File dir = new File(path);
        return dir.mkdir();
    }

    public static void addCompileDependency(Project project, String groupName, String artifactName, String artifactVersion) {
        addDependency(project,"compile",groupName,artifactName,artifactVersion);
    }

    public static Project addSubProject(Project rootProject, String projectName) {
        return ProjectBuilder.builder()
                .withName(projectName)
                .withParent(rootProject)
                .build();

    }

    public static Project buildProject(String projectName, File projectDir) {
        return ProjectBuilder.builder()
                .withName(projectName)
                .withProjectDir(projectDir)
                .build();
    }

    public static boolean fileContains(File file,String charSequence) throws FileNotFoundException {
        final Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            final String lineFromFile = scanner.nextLine();
            if(lineFromFile.contains(charSequence))
                return true;
        }
        return false;
    }

    public static void addJavaMavenBehaviour(Project project) {
        project.getPluginManager().apply(JavaPlugin.class);
        project.getRepositories().mavenCentral();
    }

    public static void addDependency(Project project, String configuration, String groupName, String artifactName, String artifactVersion) {
        project.getConfigurations()
                .getByName(configuration)
                .getDependencies()
                .add(new DefaultExternalModuleDependency(groupName, artifactName, artifactVersion));
    }
}
