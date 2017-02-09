package com.harshild.gradle.plugin.xml.parser;

import com.harshild.GradleTestHelper;
import com.harshild.gradle.plugin.entity.Project;
import com.harshild.gradle.plugin.xml.parser.XMLParser;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by harshild on 2/7/2017.
 */
public class XMLParserTest {
    @Rule
    public final TemporaryFolder testResourceDir = new TemporaryFolder();
    private File xmlFile;
    String fileContent;
    String fileContentWithNamespace;

    @Before
    public void setUp() throws IOException {
        xmlFile = testResourceDir.newFile("test.xml");
        fileContent = "<project>\n" +
                "\n" +
                "    <groupId>junit</groupId>\n" +
                "    <artifactId>junit</artifactId>\n" +
                "    <version>4.12</version>\n"+
                "</project>";

        fileContentWithNamespace = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
                "    <modelVersion>4.0.0</modelVersion>\n" +
                "\n" +
                "    <groupId>junit</groupId>\n" +
                "    <artifactId>junit</artifactId>\n" +
                "    <version>4.12</version>"+
                "</project>";

        GradleTestHelper.writeFile(xmlFile,fileContentWithNamespace);
    }

    @Test
    public void itShouldBeAbleToParseXML() throws Exception {

        GradleTestHelper.writeFile(xmlFile,fileContent);

        Project project = new XMLParser<Project>()
                .parseXML(xmlFile,Project.class);

        assertFalse(project==null);
        assertEquals("junit",project.getGroupId());
        assertEquals("junit",project.getArtifactId());
        assertEquals("4.12",project.getVersion());

    }

    @Test
    public void itShouldBeAbleToParseXMLAndIgnoreNamespace() throws Exception {
        GradleTestHelper.writeFile(xmlFile,fileContentWithNamespace);

        Project project = new XMLParser<Project>()
                .parseXML(xmlFile,Project.class);

        assertFalse(project==null);
        assertEquals("junit",project.getGroupId());
        assertEquals("junit",project.getArtifactId());
        assertEquals("4.12",project.getVersion());

    }
}
