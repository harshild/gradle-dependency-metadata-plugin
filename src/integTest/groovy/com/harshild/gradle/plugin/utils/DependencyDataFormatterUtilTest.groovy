package com.harshild.gradle.plugin.utils

import com.harshild.GradleTestHelper
import com.harshild.gradle.plugin.models.xml.parse.ProjectLicense
import com.harshild.gradle.plugin.models.xml.parse.ProjectLicenses
import com.harshild.gradle.plugin.models.xml.parse.ProjectParent
import com.harshild.gradle.plugin.models.xml.parse.XmlRootProject
import org.gradle.api.Project
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue

/**
 * Created by harshild on 2/15/2017.
 */
class DependencyDataFormatterUtilTest {

    @Rule
    public final TemporaryFolder testProjectDir = new TemporaryFolder()
    Project testProj


    @Test
    void itShouldBeAbleToDetectAProjectWithParent(){
        XmlRootProject projectWithParent =  new XmlRootProject("group",
                "artifact",
                "Test",
                "version",
                "description",
                new ProjectLicenses(Arrays.asList(
                        new ProjectLicense("",""),
                        new ProjectLicense("","")
                )),
                "http://testUrl",
                new ProjectParent("groupId","artifactId","version"))

        assertTrue(DependencyDataFormatterUtil.projectHasParent(projectWithParent))

    }

    @Test
    void itShouldTryToTakeDetailsFromParentIfNotPresent_1() throws Exception {
        testProj = GradleTestHelper.buildProject("TestProj",testProjectDir.getRoot())

        GradleTestHelper.addJavaMavenBehaviour(testProj)

        XmlRootProject projectWithParent =  new XmlRootProject("com.fasterxml.jackson.core",
                "jackson-core",
                "Test",
                "2.8.5",
                "description",
                null,
                "http://testUrl",
                new ProjectParent("com.fasterxml.jackson","jackson-parent","2.8"))

        List<XmlRootProject> xmlRootProjectList = new ArrayList<>()
        xmlRootProjectList.add(projectWithParent)

        List<XmlRootProject> newXmlRootProjectList = DependencyDataFormatterUtil.format(testProj,xmlRootProjectList)
        assertEquals(1,newXmlRootProjectList.size())

        XmlRootProject xmlRootProject = newXmlRootProjectList.get(0)
        assertTrue(xmlRootProject.getProjectLicenses()!=null)
        assertEquals(1,xmlRootProject.getProjectLicenses().getProjectLicense().size())
        assertEquals("The Apache Software License, Version 2.0",xmlRootProject.getProjectLicenses().getProjectLicense().get(0).getName())
    }

    @Test
    void itShouldTryToTakeDetailsFromParentIfNotPresent() throws Exception {
        testProj = GradleTestHelper.buildProject("TestProj",testProjectDir.getRoot())

        GradleTestHelper.addJavaMavenBehaviour(testProj)

        XmlRootProject projectWithParent =  new XmlRootProject("group",
                "artifact",
                "Test",
                null,
                "description",
                null,
                "http://testUrl",
                new ProjectParent("com.fasterxml.jackson","jackson-parent","2.8"))

        List<XmlRootProject> xmlRootProjectList = new ArrayList<>()
        xmlRootProjectList.add(projectWithParent)

        List<XmlRootProject> newXmlRootProjectList = DependencyDataFormatterUtil.format(testProj,xmlRootProjectList)
        assertEquals(1,newXmlRootProjectList.size())

        XmlRootProject xmlRootProject = newXmlRootProjectList.get(0)
        assertTrue(xmlRootProject.getProjectLicenses()!=null)
        assertEquals(1,xmlRootProject.getProjectLicenses().getProjectLicense().size())
        assertEquals("The Apache Software License, Version 2.0",xmlRootProject.getProjectLicenses().getProjectLicense().get(0).getName())
    }

}