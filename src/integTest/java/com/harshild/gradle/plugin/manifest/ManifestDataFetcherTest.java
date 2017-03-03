package com.harshild.gradle.plugin.manifest;

import com.harshild.GradleTestHelper;
import com.harshild.gradle.plugin.models.xml.parse.ProjectLicense;
import com.harshild.gradle.plugin.models.xml.parse.XmlRootProject;
import com.harshild.gradle.plugin.pom.PomDataFetcher;
import org.gradle.api.Project;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.jar.Manifest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Harshil on 25-Feb-17.
 */
public class ManifestDataFetcherTest {

    private static final String ARTIFACT_3_VERSION = "3.1.0";
    private static final String ARTIFACT_3_NAME = "biz.aQute.bndlib";
    private static final String ARTIFACT_3_GROUP = "biz.aQute.bnd";

    @Rule
    public final TemporaryFolder testProjectDir = new TemporaryFolder();

    Project testProject;

    @Before
    public void setup() throws IOException {
        testProject = GradleTestHelper.buildProject("TestProj",testProjectDir.getRoot());
        GradleTestHelper.addJavaMavenBehaviour(testProject);
        GradleTestHelper.addCompileDependency(testProject,ARTIFACT_3_GROUP,ARTIFACT_3_NAME,ARTIFACT_3_VERSION);
    }

    @Test
    public void itShouldFetchManifestFilesForProject() throws Exception {

        List<Manifest> manifestFiles = ManifestDataFetcher.getManifestFile(testProject);
        assertTrue(manifestFiles.get(0).getMainAttributes().size()>0);

    }

}