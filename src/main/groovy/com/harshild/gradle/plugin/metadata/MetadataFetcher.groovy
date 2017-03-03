package com.harshild.gradle.plugin.metadata

import com.harshild.gradle.plugin.dependency.DependencyManager
import com.harshild.gradle.plugin.models.xml.parse.XmlRootProject
import com.harshild.gradle.plugin.pom.PomDataFetcher
import org.gradle.api.Project
import org.gradle.api.artifacts.ResolvedArtifact

/**
 * Created by Harshil on 26-Feb-17.
 */
class MetadataFetcher {
    static List<XmlRootProject> generateReportData(Project project) {

        List<XmlRootProject> reportData = new ArrayList<>();

        def artifacts = new DependencyManager(project).getResolvedArtifacts('compile')
        artifacts.each { ResolvedArtifact artifact ->
            reportData.add(
                    PomDataFetcher.getDetailsFromPomForArtifact(artifact)
            )
        }
        reportData
    }
}
