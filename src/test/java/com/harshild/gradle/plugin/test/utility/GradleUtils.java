package com.harshild.gradle.plugin.test.utility;

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
public class GradleUtils {
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
}
