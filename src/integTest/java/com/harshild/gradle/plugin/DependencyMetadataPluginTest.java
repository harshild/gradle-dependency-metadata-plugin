package com.harshild.gradle.plugin;

import com.harshild.GradleTestHelper;
import com.harshild.gradle.plugin.constants.CommandConstants;
import com.harshild.gradle.plugin.task.MetadataReportGeneratorTask;
import org.gradle.testkit.runner.BuildResult;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.gradle.testkit.runner.TaskOutcome.SUCCESS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Created by harshild on 2/6/2017.
 */
public class DependencyMetadataPluginTest {
    @Rule
    public final TemporaryFolder testProjectDir = new TemporaryFolder();
    private File buildFile;
    String buildFileContent;

    @Before
    public void setup() throws IOException {
        buildFile = testProjectDir.newFile("build.gradle");
        buildFileContent = "plugins {" +
                "    id 'com.harshild.dependency-metadata'" +
                "}";
        GradleTestHelper.writeFile(buildFile, buildFileContent);
    }

    @Test
    public void plugin_has_reportGeneratorTask() throws IOException {
        BuildResult result = GradleTestHelper.executeBuild(testProjectDir,"tasks","--all");
        assertEquals(result.task(":tasks").getOutcome(), SUCCESS);
        assertTrue(result.getOutput().contains(CommandConstants.GENERATE_DEPENDENCY_METADATA_REPORT));
    }

    @Test
    public void plugin_has_reportExtension_toNotUseManifest() throws IOException {
        String compileDep =  buildFileContent + "\n"+
                "apply plugin: 'java'\n\n"
                +"repositories {\n" +
                "    mavenCentral()\n" +
                "}\n"+
                "dependencies {\n" +
                "   compile 'dom4j:dom4j:1.6.1'\n" +
                "}\n" +
                "report {\n" +
                "   includeManifestData false\n" +
                "\n}" ;
        GradleTestHelper.writeFile(buildFile, compileDep);

        BuildResult result = GradleTestHelper.executeBuild(testProjectDir, CommandConstants.GENERATE_DEPENDENCY_METADATA_REPORT);
        assertEquals(result.task(":"+CommandConstants.GENERATE_DEPENDENCY_METADATA_REPORT).getOutcome(), SUCCESS);
        assertTrue(result.getOutput().contains(MetadataReportGeneratorTask.INFO_MESSAGE));
        assertTrue(isReportFileExists());
        assertFalse(GradleTestHelper.fileContains(
                       new File(testProjectDir.getRoot().getAbsolutePath()+"/build/reports/dependency-metadata.xml"),
                "vendor"
               )
        );

    }

    private boolean isReportFileExists() {
        return new File(testProjectDir.getRoot().getAbsolutePath()+"/build/reports/dependency-metadata.xml").exists();
    }

    @Test
    public void plugin_canExecute_reportGeneratorTask() throws IOException {
        BuildResult result = GradleTestHelper.executeBuild(testProjectDir, CommandConstants.GENERATE_DEPENDENCY_METADATA_REPORT);
        assertEquals(result.task(":"+CommandConstants.GENERATE_DEPENDENCY_METADATA_REPORT).getOutcome(), SUCCESS);
        assertTrue(result.getOutput().contains(MetadataReportGeneratorTask.INFO_MESSAGE));
    }


    @Test
    public void plugin_canExecute_reportGeneratorTask_AndGenerateXMLForOneDependency() throws IOException {
        String compileDep = buildFileContent + "\n"+
                "apply plugin: 'java'\n\n"
                +"repositories {\n" +
                "    mavenCentral()\n" +
                "}\n"+
                "dependencies {\n" +
                "    compile 'junit:junit:4.12'\n" +
                "}";
        GradleTestHelper.writeFile(buildFile, compileDep);

        BuildResult result = GradleTestHelper.executeBuild(testProjectDir, CommandConstants.GENERATE_DEPENDENCY_METADATA_REPORT);
        assertEquals(result.task(":"+CommandConstants.GENERATE_DEPENDENCY_METADATA_REPORT).getOutcome(), SUCCESS);
        assertTrue(result.getOutput().contains(MetadataReportGeneratorTask.INFO_MESSAGE));
        assertTrue(isReportFileExists());
    }

    @Test
    public void plugin_canExecute_reportGeneratorTask_AndGenerateXML_SpecialCaseForDom4j() throws IOException {
        String compileDep = buildFileContent + "\n"+
                "apply plugin: 'java'\n\n"
                +"repositories {\n" +
                "    mavenCentral()\n" +
                "}\n"+
                "dependencies {\n" +
                "    compile 'dom4j:dom4j:1.6.1'\n" +
                "}";
        GradleTestHelper.writeFile(buildFile, compileDep);

        BuildResult result = GradleTestHelper.executeBuild(testProjectDir, CommandConstants.GENERATE_DEPENDENCY_METADATA_REPORT);
        assertEquals(result.task(":"+CommandConstants.GENERATE_DEPENDENCY_METADATA_REPORT).getOutcome(), SUCCESS);
        assertTrue(result.getOutput().contains(MetadataReportGeneratorTask.INFO_MESSAGE));
        assertTrue(isReportFileExists());
    }


    @Test
    public void plugin_canExecute_reportGeneratorTask_AndGenerateXMLForMultipleDependencies() throws IOException {
        String compileDep = buildFileContent + "\n"+
                "apply plugin: 'java'\n\n"
                +"repositories {\n" +
                "    mavenCentral()\n" +
                "}\n"+
                "dependencies {\n" +
                "    compile 'junit:junit:4.12'\n" +
                "    compile 'javax.inject:javax.inject:1'\n" +
                "}";
        GradleTestHelper.writeFile(buildFile, compileDep);

        BuildResult result = GradleTestHelper.executeBuild(testProjectDir, CommandConstants.GENERATE_DEPENDENCY_METADATA_REPORT);
        assertEquals(result.task(":"+CommandConstants.GENERATE_DEPENDENCY_METADATA_REPORT).getOutcome(), SUCCESS);
        assertTrue(result.getOutput().contains(MetadataReportGeneratorTask.INFO_MESSAGE));
        assertTrue(isReportFileExists());
    }

    @Test
    public void plugin_shouldFetchDetailsFromParentIfAvailable() throws IOException {
        String compileDep = buildFileContent + "\n"+
                "apply plugin: 'java'\n\n"
                +"repositories {\n" +
                "    mavenCentral()\n" +
                "}\n"+
                "dependencies {\n" +
                "    compile 'com.fasterxml.jackson.core:jackson-core:2.8.5'\n" +
                "}";
        GradleTestHelper.writeFile(buildFile, compileDep);

        BuildResult result = GradleTestHelper.executeBuild(testProjectDir, CommandConstants.GENERATE_DEPENDENCY_METADATA_REPORT);
        assertEquals(result.task(":"+CommandConstants.GENERATE_DEPENDENCY_METADATA_REPORT).getOutcome(), SUCCESS);
        assertTrue(result.getOutput().contains(MetadataReportGeneratorTask.INFO_MESSAGE));
        File outputFile = new File(testProjectDir.getRoot().getAbsolutePath() + "/build/reports/dependency-metadata.xml");
        assertTrue(outputFile.exists());
        assertTrue(GradleTestHelper.fileContains(outputFile,"The Apache Software License, Version 2.0"));
    }


    @Test
    public void plugin_shouldRun_build_before_reportGeneratorTask() throws IOException {
        BuildResult result = GradleTestHelper.executeBuild(testProjectDir,CommandConstants.GENERATE_DEPENDENCY_METADATA_REPORT);
        assertEquals(result.task(":build").getOutcome(), SUCCESS);
    }

}