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
        project.configurations.compile.resolve();
        project.configurations.getByName(configuration).resolvedConfiguration.resolvedArtifacts
    }
}
