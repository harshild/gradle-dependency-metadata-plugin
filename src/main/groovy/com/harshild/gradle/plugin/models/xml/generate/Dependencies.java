package com.harshild.gradle.plugin.models.xml.generate;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;
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

    public static Dependency addDependency() {
        return null;
    }

    @XmlElement
    public List<Dependency> getDependency() {
        return dependency;
    }

    public void setDependency(List<Dependency> dependency) {
        this.dependency = dependency;
    }
}
