package com.harshild.gradle.plugin.models.xml.parse;

import com.harshild.gradle.plugin.models.xml.generate.DependencyLicenses;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by harshild on 2/10/2017.
 */
public class ProjectLicenses
{
    private List<ProjectLicense> projectLicense;

    public ProjectLicenses() {
    }

    public ProjectLicenses(List<ProjectLicense> projectLicense) {
        this.projectLicense = projectLicense;
    }

    @XmlElement(name = "license")
    public List<ProjectLicense> getProjectLicense()
    {
        return projectLicense;
    }

    public void setProjectLicense(List<ProjectLicense> projectLicense)
    {
        this.projectLicense = projectLicense;
    }

    @Override
    public String toString() {
        return "ProjectLicenses{" +
                "projectLicense=" + projectLicense +
                '}';
    }

    public DependencyLicenses toDependencyLicences() {
        return new DependencyLicenses(
                this.projectLicense.stream()
                        .map(ProjectLicense::toDependencyLicense)
                        .collect(Collectors.toList())
        );
    }
}
