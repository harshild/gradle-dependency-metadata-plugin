package com.harshild.gradle.plugin.fileHandler.writer

import com.harshild.GradleTestHelper
import com.harshild.gradle.plugin.models.pom.parse.ProjectLicense
import com.harshild.gradle.plugin.models.pom.parse.ProjectLicenses
import com.harshild.gradle.plugin.models.pom.parse.XmlRootProject
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

import static junit.framework.TestCase.assertTrue

/**
 * Created by harshild on 2/8/2017.
 */
class XMLWriterTest {
    @Rule
    public final TemporaryFolder testResourceDir = new TemporaryFolder()
    private File xmlFile
    private XmlRootProject xmlRootProject
    private XMLWriter<XmlRootProject> projectXMLWriter


    @Before
    void setUp() throws IOException {
        xmlFile = testResourceDir.newFile("test.xml")

        xmlRootProject = new XmlRootProject("group",
                "artifact",
                "Test",
                "version",
                "description",
                new ProjectLicenses(Arrays.asList(
                        new ProjectLicense("",""),
                        new ProjectLicense("","")
                )),
                "http://testUrl")

        projectXMLWriter = new XMLWriter<>()

    }

    @Test
    void itShouldGenerateXMLForTheXMLEntity() throws Exception {
        projectXMLWriter.generate(xmlRootProject,xmlFile)
        assertTrue(xmlFile.exists())
        assertTrue(GradleTestHelper.fileContains(xmlFile,"<project>"))
    }

}