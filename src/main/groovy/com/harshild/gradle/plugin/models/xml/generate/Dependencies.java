package com.harshild.gradle.plugin.models.xml.generate;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by harshild on 2/9/2017.
 */
@XmlRootElement
public class Dependencies {
    List<Dependency> dependency;

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
}
