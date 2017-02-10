package com.harshild.gradle.plugin.models.xml.parse;


import com.harshild.gradle.plugin.models.xml.generate.Dependency;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * Created by harshild on 2/7/2017.
 */
@XmlRootElement(name = "project")
public class XmlRootProject {
    private String groupId;
    private String artifactId;
    private String name;
    private String version;
    private String description;
    private ProjectLicenses projectLicenses;
    private String url;

    public XmlRootProject() {
    }

    public XmlRootProject(String groupId, String artifactId, String name, String version, String description, ProjectLicenses projectLicenses, String url) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.name = name;
        this.version = version;
        this.description = description;
        this.projectLicenses = projectLicenses;
        this.url = url;
    }

    @XmlElement
    public String getGroupId() {
        return groupId;
    }

    void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @XmlElement
    public String getArtifactId() {
        return artifactId;
    }

    void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    @XmlElement
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

    @XmlElement
    public String getUrl() {
        return url;
    }

    void setUrl(String url) {
        this.url = url;
    }
    @XmlElement(name = "licenses")
    public ProjectLicenses getProjectLicenses() {
        return projectLicenses;
    }
    void setProjectLicenses(ProjectLicenses projectLicenses) {
        this.projectLicenses = projectLicenses;
    }


    @Override
    public String toString() {
        return "XmlRootProject{" +
                "groupId='" + groupId + '\'' +
                ", artifactId='" + artifactId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", projectLicenses=" + projectLicenses +
                ", url='" + url + '\'' +
                '}';
    }

    @XmlElement
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}

