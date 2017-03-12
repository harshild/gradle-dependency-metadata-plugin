package com.harshild.gradle.plugin.pom;

import com.harshild.GradleTestHelper;
import com.harshild.gradle.plugin.models.xml.parse.ProjectParent;
import com.harshild.gradle.plugin.pom.PomFileManager;
import org.gradle.api.Project;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created by harshild on 2/6/2017.
 */
public class PomFileManagerTest {

    private static final String ARTIFACT_1_VERSION = "4.12";
    private static final String ARTIFACT_1_NAME = "junit";
    private static final String ARTIFACT_1_GROUP = "junit";

    private static final String ARTIFACT_2_VERSION = "2.7";
    private static final String ARTIFACT_2_NAME = "log4j-core";
    private static final String ARTIFACT_2_GROUP = "org.apache.logging.log4j";

    private static final String ARTIFACT_3_VERSION = "3.1.0";
    private static final String ARTIFACT_3_NAME = "biz.aQute.bndlib";
    private static final String ARTIFACT_3_GROUP = "biz.aQute.bnd";

    @Rule
    public final TemporaryFolder testProjectDir = new TemporaryFolder();
    Project testProj;
    PomFileManager pomFileManager;

    @Before
    public void setup() throws IOException {
        testProj = GradleTestHelper.buildProject("TestProj",testProjectDir.getRoot());

        GradleTestHelper.addJavaMavenBehaviour(testProj);

        GradleTestHelper.addCompileDependency(testProj,ARTIFACT_1_GROUP,ARTIFACT_1_NAME,ARTIFACT_1_VERSION);
        GradleTestHelper.addCompileDependency(testProj,ARTIFACT_2_GROUP,ARTIFACT_2_NAME,ARTIFACT_2_VERSION);
        GradleTestHelper.addCompileDependency(testProj,ARTIFACT_3_GROUP,ARTIFACT_3_NAME,ARTIFACT_3_VERSION);

        pomFileManager = new PomFileManager(testProj);
    }



    @Test
    public void should_fetch_POMFile_location_forArtifact() throws IOException {
        Map<String,String> path = pomFileManager.getPomFileURL(ARTIFACT_3_NAME);
        assertTrue(path.get(ARTIFACT_3_NAME)
                .contains(getExpectedPomFileName(ARTIFACT_3_NAME, ARTIFACT_3_VERSION)));
    }

    @Test
    public void should_fetch_Map_Of_POMFiles_forSelectedArtifacts() throws IOException {
        Map<String,String> paths = pomFileManager.getPomFileURL(ARTIFACT_3_NAME,ARTIFACT_1_NAME);
        assertTrue(paths.get(ARTIFACT_3_NAME)
                .contains(getExpectedPomFileName(ARTIFACT_3_NAME, ARTIFACT_3_VERSION)));
        assertTrue(paths.get(ARTIFACT_1_NAME)
                .contains(getExpectedPomFileName(ARTIFACT_1_NAME, ARTIFACT_1_VERSION)));

    }

    @Test
    public void should_fetch_Map_Of_POMFiles_forArtifacts() throws IOException {
        Map<String,String> paths = pomFileManager.getPomFileURL();
        assertTrue(paths.get(ARTIFACT_3_NAME)
                .contains(getExpectedPomFileName(ARTIFACT_3_NAME, ARTIFACT_3_VERSION)));
        assertTrue(paths.get(ARTIFACT_2_NAME)
                .contains(getExpectedPomFileName(ARTIFACT_2_NAME, ARTIFACT_2_VERSION)));
        assertTrue(paths.get(ARTIFACT_1_NAME)
                .contains(getExpectedPomFileName(ARTIFACT_1_NAME, ARTIFACT_1_VERSION)));
    }

    @Test
    public void itShouldFetchPOMFileURLForDependency() throws IOException {
        ProjectParent projectParent = new ProjectParent(ARTIFACT_1_GROUP,ARTIFACT_1_NAME,ARTIFACT_1_VERSION);
        File file = pomFileManager.getPomForDependency(projectParent.getGroupId(),projectParent.getArtifactId(),projectParent.getVersion());
        assertTrue(file.exists());
        assertTrue(file.getAbsolutePath().contains(getExpectedPomFileName(ARTIFACT_1_NAME, ARTIFACT_1_VERSION)));
    }

    String getExpectedPomFileName(String artifactName, String artifactVersion){
        return artifactName+"-"+artifactVersion +".pom";
    }

}