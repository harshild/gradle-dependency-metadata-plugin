package com.harshild.gradle.plugin.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by harshild on 2/9/2017.
 */
@XmlRootElement
public class Dependencies {
    public Dependencies() {
    }

    public Dependencies(List<Dependency> dependency) {
        this.dependency = dependency;
    }

    @XmlElement
    public List<Dependency> getDependency() {
        return dependency;
    }

    public void setDependency(List<Dependency> dependency) {
        this.dependency = dependency;
    }

    List<Dependency> dependency;
}
