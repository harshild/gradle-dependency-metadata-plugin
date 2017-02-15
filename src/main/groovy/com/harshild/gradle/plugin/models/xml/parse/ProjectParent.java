package com.harshild.gradle.plugin.models.xml.parse;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by harshild on 2/15/2017.
 */

public class ProjectParent {
    private String groupId;
    private String artifactId;
    private String version;

    public ProjectParent() {
    }

    public ProjectParent(String groupId, String artifactId, String version) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
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
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "ProjectParent{" +
                "groupId='" + groupId + '\'' +
                ", artifactId='" + artifactId + '\'' +
                ", version='" + version + '\'' +
                '}';
    }


}
