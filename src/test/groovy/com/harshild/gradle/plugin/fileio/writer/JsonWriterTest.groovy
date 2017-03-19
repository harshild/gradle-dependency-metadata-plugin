package com.harshild.gradle.plugin.fileio.writer

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
 * Created by harshild on 17/03/17.
 */
class JsonWriterTest {
    @Rule
    public final TemporaryFolder testResourceDir = new TemporaryFolder()
    private File jsonFile
    private XmlRootProject xmlRootProject
    private JsonWriter<XmlRootProject> projectJsonWriter


    @Before
    void setUp() throws IOException {
        jsonFile = testResourceDir.newFile("test.json")

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

        projectJsonWriter = new JsonWriter<>()

    }

    @Test
    void itShouldGenerateJsonForTheXMLEntity() throws Exception {
        projectJsonWriter.generate(xmlRootProject, jsonFile)
        assertTrue(jsonFile.exists())
        assertTrue(GradleTestHelper.fileContains(jsonFile,"\"\",\"source\":\"POM\",\"name\":\"\"},{\"url\":\"\",\"source\":\"POM\",\"name\":\"\"}]},\"version\":\"version\",\"groupId\":\"group\",\"description\":\"description\",\"url\":\"http://testUrl\",\"name\":\"Test\",\"artifactId\":\"artifact\"}"))
    }
}
