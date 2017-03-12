package com.harshild.gradle.plugin.dependency;

import com.harshild.GradleTestHelper;
import org.gradle.api.Project;
import org.gradle.api.artifacts.ResolvedArtifact;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by harshild on 2/7/2017.
 */
public class DependencyManagerTest {

    private static final String ARTIFACT_1_VERSION = "4.12";
    private static final String ARTIFACT_1_NAME = "junit";
    private static final String ARTIFACT_1_GROUP = "junit";

    private static final String ARTIFACT_2_VERSION = "1";
    private static final String ARTIFACT_2_NAME = "javax.inject";
    private static final String ARTIFACT_2_GROUP = "javax.inject";

    private static final String ARTIFACT_3_VERSION = "3.1.0";
    private static final String ARTIFACT_3_NAME = "biz.aQute.bndlib";
    private static final String ARTIFACT_3_GROUP = "biz.aQute.bnd";

    @Rule
    public final TemporaryFolder testProjectDir = new TemporaryFolder();

    Project testProject;
    Project testProject1;
    DependencyManager dependencyManager;

    @Before
    public void setup() throws IOException {
        testProject = GradleTestHelper.buildProject("TestProj",testProjectDir.getRoot());

        GradleTestHelper.addJavaMavenBehaviour(testProject);

        GradleTestHelper.addCompileDependency(testProject,ARTIFACT_2_GROUP,ARTIFACT_2_NAME,ARTIFACT_2_VERSION);
        GradleTestHelper.addCompileDependency(testProject,ARTIFACT_3_GROUP,ARTIFACT_3_NAME,ARTIFACT_3_VERSION);

        dependencyManager = new DependencyManager(testProject);
    }


    @Test
    public void itShouldReturnASetOfResolvedDependenciesForConfiguration() throws Exception {
        Set<ResolvedArtifact> artifacts = dependencyManager.getResolvedArtifacts("compile");
        assertEquals(2,artifacts.size());
    }

    @Test
    public void itShouldReturnASetOfResolvedDependenciesForConfigurationIncludingTransitiveDependencies() throws Exception {
        GradleTestHelper.addCompileDependency(testProject,ARTIFACT_1_GROUP,ARTIFACT_1_NAME,ARTIFACT_1_VERSION);

        Set<ResolvedArtifact> artifacts = dependencyManager.getResolvedArtifacts("compile");
        assertEquals(4,artifacts.size());
    }

    @Test
    public void itShouldReturnASetOfResolvedDependenciesForSubProjectsAlso() throws Exception {
        testProject1 = GradleTestHelper.addSubProject(testProject,"TestSub");
        GradleTestHelper.addJavaMavenBehaviour(testProject1);

        GradleTestHelper.addCompileDependency(testProject1,ARTIFACT_1_GROUP,ARTIFACT_1_NAME,ARTIFACT_1_VERSION);
        Set<ResolvedArtifact> resolvedArtifacts = dependencyManager.getResolvedArtifacts("compile");
        assertEquals(4,resolvedArtifacts.size());
    }

    @Test
    public void itShouldResolveDependencyForAllConfigurations() throws Exception {
        GradleTestHelper.addDependency(testProject,"testCompile",ARTIFACT_1_GROUP,ARTIFACT_1_NAME,ARTIFACT_1_VERSION);

        Set<ResolvedArtifact> resolvedArtifacts = dependencyManager.getResolvedArtifacts();
        assertEquals(4,resolvedArtifacts.size());
    }

    @Test
    public void itShouldResolveDependencyForSelectiveConfigurations() throws Exception {
        GradleTestHelper.addDependency(testProject,"testCompile",ARTIFACT_1_GROUP,ARTIFACT_1_NAME,ARTIFACT_1_VERSION);

        Set<ResolvedArtifact> resolvedArtifacts = dependencyManager.getResolvedArtifacts("compile");
        assertEquals(2,resolvedArtifacts.size());
    }


}