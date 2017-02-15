package com.harshild.gradle.plugin.utils

import com.harshild.gradle.plugin.dependency.PomFileManager
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

        xmlRootProjects.each {
            if(projectHasParent(it)){
                def parent = it.projectParent
                def parentPom = new PomFileManager(project).getPomForDependency(parent.groupId,parent.artifactId,parent.version)
                def parentProject = new XMLParser<XmlRootProject>().parseXML(parentPom,XmlRootProject.class)

                it.groupId = it.groupId!=""&& it.groupId!=null ? it.groupId :  parentProject.groupId
                it.version = it.version!=""&& it.version!=null ? it.version :  parentProject.version

                it.projectLicenses = it.projectLicenses!=null ? it.projectLicenses :  new ProjectLicenses()
                it.projectLicenses.projectLicense = it.projectLicenses.projectLicense!=null && it.projectLicenses.projectLicense.size() != 0 ?
                        it.projectLicenses.projectLicense :  new ArrayList<>()

                if(parentProject.projectLicenses!= null && parentProject.projectLicenses.projectLicense != null) {
                    parentProject.projectLicenses.projectLicense.each { license ->
                        if(!it.projectLicenses.projectLicense.contains(new ProjectLicense(license.name,license.url)))
                            it.projectLicenses.projectLicense.add(license)
                    }
                }

                it
            }
            else{
                it
            }
        }
    }

    static boolean projectHasParent(XmlRootProject projectWithParent) {
        return projectWithParent.projectParent!=null
    }
}
