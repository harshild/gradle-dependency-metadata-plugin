package com.harshild.gradle.plugin.dependency

import groovy.util.slurpersupport.GPathResult
import org.gradle.api.Project
import org.gradle.api.artifacts.ResolvedArtifact
import org.gradle.api.component.Artifact
import org.gradle.maven.MavenModule
import org.gradle.maven.MavenPomArtifact

/**
 * Created by harshild on 2/6/2017.
 */
class PomFileManager {
    Project project

    def PomFileManager(Project project){
        this.project = project;
    }

    def getPomFromArtifact = { Artifact artifact ->
        def component = project.dependencies.createArtifactResolutionQuery()
                .forComponents(artifact.id.componentIdentifier)
                .withArtifacts(MavenModule, MavenPomArtifact)
                .execute()
                .resolvedComponents[0]
        def pomFile= component.getArtifacts(MavenPomArtifact)[0].file
        return pomFile
    }

    Map getPomFileURL(String ... dependencyNames) {
        Map res = new HashMap();
        project.configurations.compile.resolve();
        project.configurations.compile.resolvedConfiguration.resolvedArtifacts.each { ResolvedArtifact art ->
            if (dependencyNames.length==0 || dependencyNames.contains(art.name))
                res.put(art.name,getPomFromArtifact(art))
        }
        res
    }
}
