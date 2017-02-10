package com.harshild.gradle.plugin.models.xml.generate;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.Arrays;

/**
 * Created by harshild on 2/10/2017.
 */

public class Dependency {
    private String groupId;
    private String artifactId;
    private String name;
    private String version;
    private String description;
    private DependencyLicenses dependencyLicenses;
    private String url;

    public Dependency() {
    }

    public Dependency(String groupId, String artifactId, String name, String version, String description, DependencyLicenses dependencyLicenses, String url) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.name = name;
        this.version = version;
        this.description = description;
        this.dependencyLicenses = dependencyLicenses;
        this.url = url;
    }

    @XmlAttribute
    public String getGroupId() {
        return groupId;
    }

    void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @XmlAttribute
    public String getArtifactId() {
        return artifactId;
    }

    void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    @XmlAttribute
    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    @XmlAttribute
    public String getUrl() {
        return url;
    }

    void setUrl(String url) {
        this.url = url;
    }
    @XmlElement(name = "licenses")
    public DependencyLicenses getDependencyLicenses() {
        return dependencyLicenses;
    }
    void setDependencyLicenses(DependencyLicenses dependencyLicenses) {
        this.dependencyLicenses = dependencyLicenses;
    }


    @Override
    public String toString() {
        return "Dependency{" +
                "groupId='" + groupId + '\'' +
                ", artifactId='" + artifactId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dependencyLicenses=" + dependencyLicenses +
                ", url='" + url + '\'' +
                '}';
    }

    @XmlAttribute
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
    public static DependencyLicenses createLicenses(DependencyLicense... dependencyLicense){
        return new DependencyLicenses(Arrays.asList(dependencyLicense));
    }

    public static DependencyLicense createLicense(String name, String url){
        return new DependencyLicense(name,url);
    }
}

