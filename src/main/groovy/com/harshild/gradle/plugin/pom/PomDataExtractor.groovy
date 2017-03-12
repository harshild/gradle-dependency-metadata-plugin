package com.harshild.gradle.plugin.pom

import com.harshild.gradle.plugin.models.xml.parse.XmlRootProject
import com.harshild.gradle.plugin.utils.DependencyDataFormatterUtil
import com.harshild.gradle.plugin.xml.parser.XMLParser
import org.gradle.api.Project
import org.gradle.api.artifacts.ResolvedArtifact;

/**
 * Created by Harshil on 25-Feb-17.
 */
class PomDataExtractor {
    public static final String POM_SOURCE = "POM"

    static List<XmlRootProject> getDetailsFromPomForProject(Project project) {
        Map<String, String> dependencyPomURLMap = new PomFileManager(project).getPomFileURL()
        def parser = new XMLParser<XmlRootProject>()
        List<XmlRootProject> parsedList = parser.parseXMLs(dependencyPomURLMap, XmlRootProject.class)
        DependencyDataFormatterUtil.format(project, parsedList)
        parsedList
    }

    static XmlRootProject getDetailsFromPomForArtifact(Project project,ResolvedArtifact artifact) {
        File dependencyPom = new PomFileManager(project)
                .getPomFromArtifact(artifact)

        def parser = new XMLParser<XmlRootProject>()
        XmlRootProject parsed = parser.parseXML(dependencyPom, XmlRootProject.class)
        DependencyDataFormatterUtil.format(project, parsed)
        parsed
    }
}
