package com.harshild.gradle.plugin.manifest

import com.harshild.GradleTestHelper
import com.harshild.gradle.plugin.dependency.DependencyManager
import org.gradle.api.Project
import org.gradle.api.artifacts.ResolvedArtifact
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

import java.io.IOException
import java.util.HashMap
import java.util.jar.Attributes
import java.util.jar.Manifest

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue

/**
 * Created by Harshil on 25-Feb-17.
 */
class ManifestDataExtractorTest {

    private static final String ARTIFACT_3_VERSION = "3.1.0"
    private static final String ARTIFACT_3_NAME = "biz.aQute.bndlib"
    private static final String ARTIFACT_3_GROUP = "biz.aQute.bnd"

    @Rule
    public final TemporaryFolder testProjectDir = new TemporaryFolder()

    private Project testProject

    @Before
    void setup() throws IOException {
        testProject = GradleTestHelper.buildProject("TestProj",testProjectDir.getRoot())
        GradleTestHelper.addJavaMavenBehaviour(testProject)
        GradleTestHelper.addCompileDependency(testProject,ARTIFACT_3_GROUP,ARTIFACT_3_NAME,ARTIFACT_3_VERSION)
    }

    @Test
    void itShouldFetchManifestFilesForProject() throws Exception {
        for(ResolvedArtifact artifact :new DependencyManager(testProject).getResolvedArtifacts("compile")) {
            Manifest manifestFile = ManifestDataExtractor.getManifestFile(artifact)
            assertTrue(manifestFile.getMainAttributes().size() > 0)
            assertTrue(manifestFile.getMainAttributes().containsKey(new Attributes.Name("Bundle-Name")))
        }
    }

    @Test
    void itShouldFetchFieldAndValueAsMap() throws Exception {
        for(ResolvedArtifact artifact :new DependencyManager(testProject).getResolvedArtifacts("compile")) {
            HashMap<String,String> manifestData = ManifestDataExtractor.getManifestDataForArtifact(artifact)
            assertTrue(manifestData.containsKey("Bundle-Name"))
        }
    }

}