package com.harshild.gradle.plugin.entity;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by harshild on 2/7/2017.
 */
public class License
{
    private String name;

    private String url;

    public License() {
    }

    public License(String name, String url) {
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
    public String toString() {
        return "License{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
