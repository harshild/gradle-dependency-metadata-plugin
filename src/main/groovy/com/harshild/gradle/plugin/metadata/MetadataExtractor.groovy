package com.harshild.gradle.plugin.metadata

import com.harshild.gradle.plugin.dependency.DependencyManager
import com.harshild.gradle.plugin.manifest.ManifestDataExtractor
import com.harshild.gradle.plugin.models.xml.parse.ProjectLicense
import com.harshild.gradle.plugin.models.xml.parse.XmlRootProject
import com.harshild.gradle.plugin.pom.PomDataExtractor
import org.gradle.api.Project
import org.gradle.api.artifacts.ResolvedArtifact

import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Created by Harshil on 26-Feb-17.
 */
class MetadataExtractor {
    static List<XmlRootProject> generateReportData(Project project) {

        List<XmlRootProject> reportData = new ArrayList<>()

        def artifacts = new DependencyManager(project).getResolvedArtifacts('compile')
        artifacts.each { ResolvedArtifact artifact ->
            reportData.add(
                    getReportDataForArtifact(project,artifact)
            )
        }
        reportData
    }

    private static XmlRootProject getReportDataForArtifact(Project project , ResolvedArtifact artifact) {
        XmlRootProject xmlProject = PomDataExtractor.getDetailsFromPomForArtifact(project, artifact)
        def manifest = ManifestDataExtractor.getManifestFile(artifact)

        if (manifest) {
            def attributes = manifest.mainAttributes

            xmlProject.name = checkNull(xmlProject.name) ? attributes.getValue('Bundle-Name') ?: attributes.getValue('Implementation-Title') ?: attributes.getValue('Bundle-SymbolicName') : xmlProject.name
            xmlProject.version = checkNull(xmlProject.version) ? attributes.getValue('Bundle-Version') ?: attributes.getValue('Implementation-Version') ?: attributes.getValue('Specification-Version') : xmlProject.version
            xmlProject.description = checkNull(xmlProject.description) ? attributes.getValue('Bundle-Description') : xmlProject.description
            if(attributes.getValue('Bundle-License'))
                xmlProject.projectLicenses.projectLicense.add(getProjectLicense(attributes.getValue('Bundle-License')))
            xmlProject.vendor = checkNull(xmlProject.vendor) ? attributes.getValue('Bundle-Vendor') ?: attributes.getValue('Implementation-Vendor') : xmlProject.vendor
            xmlProject.url = checkNull(xmlProject.url) ? attributes.getValue('Bundle-DocURL') : xmlProject.url
        }
        xmlProject
    }

    private static ProjectLicense getProjectLicense(String licenseValue) {
        if(isURL(licenseValue))
            new ProjectLicense('no name available',licenseValue)
        else
            new ProjectLicense(licenseValue, 'no url available')
    }

     static boolean isURL(String licenseValue) {
        String URL_REGEX = "^((http?|ftp|https)://|(www|ftp)\\.)?[a-z0-9-]+(\\.[a-z0-9-]+)+([/?].*)?\$"

        Pattern p = Pattern.compile(URL_REGEX)
        Matcher m = p.matcher(licenseValue)
        m.find()
    }

    private static checkNull(String s) {
        return s== null || s.trim()=="" || s==''
    }
}
