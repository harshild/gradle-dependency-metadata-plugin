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

    Set<ResolvedArtifact> getResolvedArtifacts(String... configurations){
        Set<ResolvedArtifact> artifactSet = new HashSet<>()
        List<String> availableConfigurations = getAllConfigurations(project,configurations)
        availableConfigurations.each { availableConfiguration ->
            project.configurations.getByName(availableConfiguration).resolve()
            project.allprojects.each { sub->
                    artifactSet.addAll(sub.configurations.getByName(availableConfiguration).resolvedConfiguration.resolvedArtifacts)
            }
            artifactSet.addAll(project.configurations.getByName(availableConfiguration).resolvedConfiguration.resolvedArtifacts)
        }
        artifactSet
    }

    private static List<String> getAllConfigurations(Project project,String... configurations) {
        List<String> toBeReturned = new ArrayList<>();
        for( def config:project.configurations) {
            if(configurations.length == 0 || configurations.contains(config.name))
                toBeReturned.add(config.name)
        }
        toBeReturned
    }
}
