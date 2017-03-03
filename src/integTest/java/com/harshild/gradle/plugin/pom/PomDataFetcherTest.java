package com.harshild.gradle.plugin.pom;

import com.harshild.GradleTestHelper;
import com.harshild.gradle.plugin.models.xml.parse.ProjectLicense;
import com.harshild.gradle.plugin.models.xml.parse.XmlRootProject;
import org.gradle.api.Project;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Harshil on 25-Feb-17.
 */
public class PomDataFetcherTest {

    private static final String ARTIFACT_2_VERSION = "1";
    private static final String ARTIFACT_2_NAME = "javax.inject";
    private static final String ARTIFACT_2_GROUP = "javax.inject";

    @Rule
    public final TemporaryFolder testProjectDir = new TemporaryFolder();

    Project testProject;

    @Before
    public void setup() throws IOException {
        testProject = GradleTestHelper.buildProject("TestProj",testProjectDir.getRoot());
        GradleTestHelper.addJavaMavenBehaviour(testProject);
        GradleTestHelper.addCompileDependency(testProject,ARTIFACT_2_GROUP,ARTIFACT_2_NAME,ARTIFACT_2_VERSION);

    }

    @Test
    public void getDetailsFromPom() throws Exception {
        List<XmlRootProject> detailsFromPomForProject = PomDataFetcher.getDetailsFromPomForProject(testProject);
        assertEquals(1,detailsFromPomForProject.size());
        assertEquals(ARTIFACT_2_NAME,detailsFromPomForProject.get(0).getArtifactId());
        assertEquals(ARTIFACT_2_GROUP,detailsFromPomForProject.get(0).getGroupId());
        assertEquals(ARTIFACT_2_VERSION,detailsFromPomForProject.get(0).getVersion());
    }

    @Test
    public void itShouldInsertSourceLabelAsPOMForLicenseData(){
        List<XmlRootProject> detailsFromPomForProject =
                PomDataFetcher.getDetailsFromPomForProject(testProject);

        assertEquals(1,detailsFromPomForProject.size());
        assertEquals(ARTIFACT_2_NAME,detailsFromPomForProject.get(0).getArtifactId());
        assertEquals(ARTIFACT_2_GROUP,detailsFromPomForProject.get(0).getGroupId());
        assertEquals(ARTIFACT_2_VERSION,detailsFromPomForProject.get(0).getVersion());

        ProjectLicense projectLicense = detailsFromPomForProject.get(0)
                .getProjectLicenses().getProjectLicense().get(0);
        assertEquals(PomDataFetcher.POM_SOURCE,projectLicense.getSource());
    }

}