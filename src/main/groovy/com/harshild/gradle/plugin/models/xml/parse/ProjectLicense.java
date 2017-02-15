package com.harshild.gradle.plugin.models.xml.parse;

import com.harshild.gradle.plugin.models.xml.generate.DependencyLicense;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by harshild on 2/10/2017.
 */
public class ProjectLicense
{
    private String name;

    private String url;

    public ProjectLicense() {
    }

    public ProjectLicense(String name, String url) {
        this.name = name;
        this.url = url;
    }

    @XmlElement
    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    @XmlElement
    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectLicense that = (ProjectLicense) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return url != null ? url.equals(that.url) : that.url == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProjectLicense{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public DependencyLicense toDependencyLicense() {
        return new DependencyLicense(this.name,this.url);
    }
}
