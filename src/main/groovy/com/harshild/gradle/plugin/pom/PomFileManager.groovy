package com.harshild.gradle.plugin.pom

import com.harshild.gradle.plugin.dependency.DependencyManager
import org.gradle.api.Project
import org.gradle.api.artifacts.ResolvedArtifact
import org.gradle.api.artifacts.component.ComponentIdentifier
import org.gradle.internal.component.external.model.DefaultModuleComponentIdentifier
import org.gradle.maven.MavenModule
import org.gradle.maven.MavenPomArtifact

/**
 * Created by harshild on 2/6/2017.
 */
class PomFileManager {
    Project project
    DependencyManager dependencyManager

    PomFileManager() {
    }

    PomFileManager(Project project){
        this.project = project
        dependencyManager = new DependencyManager(project)
    }

    def getPomFromArtifact = { ResolvedArtifact artifact ->
        return getPom(artifact.id.componentIdentifier)
    }

    File getPomForDependency(String groupId, String artifactId, String version)  {
        getPom(new DefaultModuleComponentIdentifier(groupId,artifactId,version))
    }

    File getPom(ComponentIdentifier componentIdentifier) {
        def component = project.dependencies.createArtifactResolutionQuery()
                .forComponents(componentIdentifier)
                .withArtifacts(MavenModule, MavenPomArtifact)
                .execute()
                .resolvedComponents[0]
        def pomFile = component.getArtifacts(MavenPomArtifact)[0].file
        return pomFile
    }

    Map<String,String> getPomFileURL(String ... dependencyNames) {
        Map<String,String> result = new HashMap()

        dependencyManager.getResolvedArtifacts("compile").each { ResolvedArtifact resolvedArtifact ->
            if (dependencyNames.length==0 || dependencyNames.contains(resolvedArtifact.name))
                result.put(resolvedArtifact.name,getPomFromArtifact(resolvedArtifact).path)
        }
        result
    }
}
