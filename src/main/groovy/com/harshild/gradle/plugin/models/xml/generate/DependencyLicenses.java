package com.harshild.gradle.plugin.models.xml.generate;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * Created by harshild on 2/10/2017.
 */
public class DependencyLicenses
{
    private List<DependencyLicense> dependencyLicense;

    public DependencyLicenses() {
    }

    public DependencyLicenses(List<DependencyLicense> dependencyLicense) {
        this.dependencyLicense = dependencyLicense;
    }

    @XmlElement(name = "license")
    public List<DependencyLicense> getDependencyLicense()
    {
        return dependencyLicense;
    }

    public void setDependencyLicense(List<DependencyLicense> dependencyLicense)
    {
        this.dependencyLicense = dependencyLicense;
    }

    @Override
    public String toString() {
        return "ProjectLicenses{" +
                "dependencyLicense=" + dependencyLicense +
                '}';
    }
}
