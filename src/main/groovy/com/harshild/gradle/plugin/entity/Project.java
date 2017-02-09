package com.harshild.gradle.plugin.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by harshild on 2/7/2017.
 */
@XmlRootElement
public class Project {
    private String groupId;
    private String artifactId;
    private String name;
    private String version;
    private String description;
    private Licenses licenses;
    private String url;

    public Project() {
    }

    public Project(String groupId, String artifactId, String name, String version, String description, Licenses licenses, String url) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.name = name;
        this.version = version;
        this.description = description;
        this.licenses = licenses;
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
    @XmlElement
    public Licenses getLicenses() {
        return licenses;
    }
    void setLicenses(Licenses licenses) {
        this.licenses = licenses;
    }


    @Override
    public String toString() {
        return "Project{" +
                "groupId='" + groupId + '\'' +
                ", artifactId='" + artifactId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", licenses=" + licenses +
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
