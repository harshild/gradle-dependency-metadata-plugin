package com.harshild.gradle.plugin.extension

import com.harshild.GradleTestHelper
import org.gradle.testkit.runner.BuildResult
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

import java.io.File
import java.io.IOException

import static org.gradle.testkit.runner.TaskOutcome.SUCCESS
import static org.junit.Assert.*

/**
 * Created by harshild on 12/03/17.
 */
class ReportExtensionTest {
    @Rule
    public final TemporaryFolder testProjectDir = new TemporaryFolder()
    private File buildFile
    String buildFileContent

    @Before
    void setup() throws IOException {
        buildFile = testProjectDir.newFile("build.gradle")
        buildFileContent = "plugins {" +
                "    id 'com.harshild.dependency-metadata'" +
                "}"
        GradleTestHelper.writeFile(buildFile, buildFileContent)
    }

    @Test
    void reportExtensionWorksWithPlugin() throws IOException {
        String compileDep =  buildFileContent + "\n"+
                "report {\n" +
                "\n}"
        GradleTestHelper.writeFile(buildFile, compileDep)

        BuildResult result = GradleTestHelper.executeBuild(testProjectDir,"build")
        assertEquals(result.task(":build").getOutcome(), SUCCESS)
    }

    @Test
    void reportExtensionWithRequiredOptionsWorksWithPlugin() throws IOException {
        String compileDep =  buildFileContent + "\n"+
                "report {\n" +
                "includeManifestData false\n" +
                "\n}"
        GradleTestHelper.writeFile(buildFile, compileDep)

        BuildResult result = GradleTestHelper.executeBuild(testProjectDir,"build")
        assertEquals(result.task(":build").getOutcome(), SUCCESS)
    }

    @Test
    void reportExtensionWithOutputFormatWorksWithPlugin() throws IOException {
        String compileDep =  buildFileContent + "\n"+
                "report {\n" +
                "outputFormat 'json'\n" +
                "\n}"
        GradleTestHelper.writeFile(buildFile, compileDep)

        BuildResult result = GradleTestHelper.executeBuild(testProjectDir,"build")
        assertEquals(result.task(":build").getOutcome(), SUCCESS)
    }

}