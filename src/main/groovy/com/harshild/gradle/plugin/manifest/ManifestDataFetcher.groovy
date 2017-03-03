package com.harshild.gradle.plugin.manifest

import com.harshild.gradle.plugin.dependency.DependencyManager
import org.gradle.api.Project
import org.gradle.internal.impldep.com.google.common.io.Files

import java.util.jar.JarFile
import java.util.jar.Manifest;

/**
 * Created by Harshil on 26-Feb-17.
 */
class ManifestDataFetcher {
    static List<Manifest> getManifestFile(Project project) {
        List<Manifest> m = new ArrayList<>();
        new DependencyManager(project).getResolvedArtifacts('compile').each { artifact ->
                    m.add( new JarFile(artifact.file).manifest)
        }
        m
    }
}
