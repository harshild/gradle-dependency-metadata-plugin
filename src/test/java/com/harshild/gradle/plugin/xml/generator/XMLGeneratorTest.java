package com.harshild.gradle.plugin.xml.generator;

import com.harshild.GradleTestHelper;
import com.harshild.gradle.plugin.entity.License;
import com.harshild.gradle.plugin.entity.Licenses;
import com.harshild.gradle.plugin.entity.Project;
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
    private Project project;
    private XMLGenerator<Project> projectXMLGenerator;


    @Before
    public void setUp() throws IOException {
        xmlFile = testResourceDir.newFile("test.xml");

        project = new Project("group",
                "artifact",
                "Test",
                "version",
                "description",
                new Licenses(Arrays.asList(
                        new License("Apache 2.0","http://URL"),
                        new License("BSD","http://TheURL"))),
                "http://testUrl");

        projectXMLGenerator = new XMLGenerator<>();

    }

    @Test
    public void itShouldGenerateXMLForTheXMLEntity() throws Exception {
        projectXMLGenerator.generateXML(project,xmlFile);
        assertTrue(xmlFile.exists());
        assertTrue(GradleTestHelper.fileContains(xmlFile,"<project>"));
    }

}