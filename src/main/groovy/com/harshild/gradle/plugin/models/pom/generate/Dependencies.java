package com.harshild.gradle.plugin.models.pom.generate;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by harshild on 2/9/2017.
 */
@XmlRootElement
public class Dependencies {
    private String projectName;
    private String projectGroup;
    private String projectVersion;

    List<Dependency> dependency;

    public Dependencies() {
    }

    public Dependencies(List<Dependency> dependency) {
        this.dependency = dependency;
    }

    @XmlAttribute
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @XmlAttribute
    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    @XmlAttribute
    public String getProjectVersion() {
        return projectVersion;
    }

    public void setProjectVersion(String projectVersion) {
        this.projectVersion = projectVersion;
    }

    @XmlElement
    public List<Dependency> getDependency() {
        return dependency;
    }

    public void setDependency(List<Dependency> dependency) {
        this.dependency = dependency;
    }
}
