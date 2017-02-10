package com.harshild.gradle.plugin.xml.generator;

import com.harshild.GradleTestHelper;
import com.harshild.gradle.plugin.models.xml.parse.ProjectLicense;
import com.harshild.gradle.plugin.models.xml.parse.ProjectLicenses;
import com.harshild.gradle.plugin.models.xml.parse.XmlRootProject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by harshild on 2/8/2017.
 */
public class XMLGeneratorTest {
    @Rule
    public final TemporaryFolder testResourceDir = new TemporaryFolder();
    private File xmlFile;
    private XmlRootProject xmlRootProject;
    private XMLGenerator<XmlRootProject> projectXMLGenerator;


    @Before
    public void setUp() throws IOException {
        xmlFile = testResourceDir.newFile("test.xml");

        xmlRootProject = new XmlRootProject("group",
                "artifact",
                "Test",
                "version",
                "description",
                new ProjectLicenses(Arrays.asList(
                        new ProjectLicense("",""),
                        new ProjectLicense("","")
                )),
                "http://testUrl");

        projectXMLGenerator = new XMLGenerator<>();

    }

    @Test
    public void itShouldGenerateXMLForTheXMLEntity() throws Exception {
        projectXMLGenerator.generateXML(xmlRootProject,xmlFile);
        assertTrue(xmlFile.exists());
        assertTrue(GradleTestHelper.fileContains(xmlFile,"<project>"));
    }

}