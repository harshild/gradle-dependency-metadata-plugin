package com.harshild.gradle.plugin.utils

import com.harshild.gradle.plugin.pom.PomFileManager
import com.harshild.gradle.plugin.models.xml.parse.ProjectLicense
import com.harshild.gradle.plugin.models.xml.parse.ProjectLicenses
import com.harshild.gradle.plugin.models.xml.parse.XmlRootProject
import com.harshild.gradle.plugin.xml.parser.XMLParser
import org.gradle.api.Project

/**
 * Created by harshild on 2/15/2017.
 */
class DependencyDataFormatterUtil {
    static List<XmlRootProject> format(Project project, List<XmlRootProject> xmlRootProjects) {

        xmlRootProjects.each {XmlRootProject rootProject ->
            format(project,rootProject)
        }
    }

    static XmlRootProject format(Project project, XmlRootProject xmlRootProject) {
            syncDetailsFromParent(project,xmlRootProject)
    }

    private def static XmlRootProject syncDetailsFromParent(Project project,XmlRootProject rootProject) {
        if (projectHasParent(rootProject)) {
            def parent = rootProject.projectParent
            def parentPom = new PomFileManager(project).getPomForDependency(parent.groupId, parent.artifactId, parent.version)
            def parentProject = new XMLParser<XmlRootProject>().parseXML(parentPom, XmlRootProject.class)

            parentProject = syncDetailsFromParent(project,parentProject)

            rootProject.groupId = rootProject.groupId != "" && rootProject.groupId != null ? rootProject.groupId : parentProject.groupId
            rootProject.version = rootProject.version != "" && rootProject.version != null ? rootProject.version : parentProject.version
            rootProject.url = rootProject.url != "" && rootProject.url != null ? rootProject.url : parentProject.url


            rootProject.projectLicenses = rootProject.projectLicenses != null ? rootProject.projectLicenses : new ProjectLicenses()
            rootProject.projectLicenses.projectLicense = rootProject.projectLicenses.projectLicense != null && rootProject.projectLicenses.projectLicense.size() != 0 ?
                    rootProject.projectLicenses.projectLicense : new ArrayList<>()

            if (parentProject.projectLicenses != null && parentProject.projectLicenses.projectLicense != null) {
                parentProject.projectLicenses.projectLicense.each { license ->
                    if (!rootProject.projectLicenses.projectLicense.contains(new ProjectLicense(license.name, license.url)))
                        rootProject.projectLicenses.projectLicense.add(license)
                }
            }

            rootProject
        } else {
            rootProject
        }
    }

    static boolean projectHasParent(XmlRootProject projectWithParent) {
        return projectWithParent.projectParent!=null
    }
}
