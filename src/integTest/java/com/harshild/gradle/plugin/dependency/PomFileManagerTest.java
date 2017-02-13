package com.harshild.gradle.plugin.dependency;

import com.harshild.GradleTestHelper;
import org.gradle.api.Project;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

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
                .contains(getPomfileName(ARTIFACT_3_NAME, ARTIFACT_3_VERSION)));
    }

    @Test
    public void should_fetch_Map_Of_POMFiles_forSelectedArtifacts() throws IOException {
        Map<String,String> paths = pomFileManager.getPomFileURL(ARTIFACT_3_NAME,ARTIFACT_1_NAME);
        assertTrue(paths.get(ARTIFACT_3_NAME)
                .contains(getPomfileName(ARTIFACT_3_NAME, ARTIFACT_3_VERSION)));
        assertTrue(paths.get(ARTIFACT_1_NAME)
                .contains(getPomfileName(ARTIFACT_1_NAME, ARTIFACT_1_VERSION)));

    }

    @Test
    public void should_fetch_Map_Of_POMFiles_forArtifacts() throws IOException {
        Map<String,String> paths = pomFileManager.getPomFileURL();
        assertTrue(paths.get(ARTIFACT_3_NAME)
                .contains(getPomfileName(ARTIFACT_3_NAME, ARTIFACT_3_VERSION)));
        assertTrue(paths.get(ARTIFACT_2_NAME)
                .contains(getPomfileName(ARTIFACT_2_NAME, ARTIFACT_2_VERSION)));
        assertTrue(paths.get(ARTIFACT_1_NAME)
                .contains(getPomfileName(ARTIFACT_1_NAME, ARTIFACT_1_VERSION)));
    }

    String getPomfileName(String artifactName,String artifactVersion){
        return artifactName+"-"+artifactVersion +".pom";
    }

}