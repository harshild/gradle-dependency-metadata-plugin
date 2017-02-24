package com.harshild.gradle.plugin.models.xml.marshaller

import com.harshild.gradle.plugin.models.xml.generate.Dependencies
import com.harshild.gradle.plugin.models.xml.generate.Dependency
import com.harshild.gradle.plugin.models.xml.parse.XmlRootProject

/**
 * Created by harshild on 2/10/2017.
 */
class Marshaller {
    static Dependency marshall(XmlRootProject project) {
        new Dependency(
                project.groupId,
                project.artifactId,
                project.name,
                project.version,
                project.description,
                project.projectLicenses == null? null :project.projectLicenses.toDependencyLicences(),
                project.url
        )
    }

    static Dependencies marshall(XmlRootProject... project) {
        marshall(Arrays.asList(project),"","","")
    }

    static Dependencies marshall(List<XmlRootProject> project, String projectName, String projectVersion, String projectGroup) {
        List<Dependency> dependencyList = new ArrayList<>()
        project.each {
            dependencyList.add(marshall(it))
        }
        return new Dependencies(dependencyList)
    }
}
