package com.harshild.gradle.plugin.models.xml.generate;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by harshild on 2/10/2017.
 */
public class DependencyLicense
{
    private String name;

    private String url;

    public DependencyLicense() {
    }

    public DependencyLicense(String name, String url) {
        this.name = name;
        this.url = url;
    }

    @XmlAttribute
    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    @XmlAttribute
    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ProjectLicense{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
