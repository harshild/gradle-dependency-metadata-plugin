package com.harshild.gradle.plugin.dependency

import org.gradle.api.Project
import org.gradle.api.artifacts.ResolvedArtifact

/**
 * Created by harshild on 2/7/2017.
 */
class DependencyManager {
    Project project

    DependencyManager(Project project) {
        this.project = project
    }

    Set<ResolvedArtifact> getResolvedArtifacts(String configuration){
        Set<ResolvedArtifact> artifactSet = new HashSet<>()
        project.configurations.compile.resolve()
        project.allprojects.each { sub->
            artifactSet.addAll(sub.configurations.getByName(configuration).resolvedConfiguration.resolvedArtifacts)
        }

        artifactSet.addAll(project.configurations.getByName(configuration).resolvedConfiguration.resolvedArtifacts)

        artifactSet
    }
}
