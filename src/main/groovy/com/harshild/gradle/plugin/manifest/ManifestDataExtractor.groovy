package com.harshild.gradle.plugin.manifest

import org.gradle.api.artifacts.ResolvedArtifact

import java.util.jar.JarFile
import java.util.jar.Manifest

/**
 * Created by Harshil on 26-Feb-17.
 */
class ManifestDataExtractor {
    static Manifest getManifestFile(ResolvedArtifact resolvedArtifact) {
        new JarFile(resolvedArtifact.file).manifest
    }

    static HashMap<String, String> getManifestDataForArtifact(ResolvedArtifact artifact) {
        Map<String,String> map = new HashMap<String,String>()
        getManifestFile(artifact).getMainAttributes().each { attributeKey, value ->
            map.put(attributeKey.name,value)
        }
        map
    }
}
